package com.plant.server.business.services.admin;

import com.plant.server.business.entities.admin.Admin;
import com.plant.server.business.entities.admin.AdminRepository;
import com.plant.server.business.entities.user.UserRepository;
import com.plant.server.business.services.admin.cos.AdminCO;
import com.plant.server.business.services.exceptions.ApiRuntimeException;
import com.plant.server.business.services.exceptions.DuplicateInstanceException;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.Chunk;
import com.plant.server.util.collection.IterableUtil;
import com.plant.server.web.controller.api.ApiErrorCodes;
import com.plant.server.web.controller.api.form.AdminForm;
import com.plant.server.web.controller.api.form.register.RegisterAdminForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminCO registerAdmin(RegisterAdminForm adminForm) {

        checkEmail(adminForm);

        Admin admin = toRegisterAdmin(adminForm);

        adminRepository.save(admin);

        return getAdmin(admin.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public AdminCO getAdmin(Long adminId) {
        Optional<Admin> admin = this.adminRepository.findById(adminId);
        if (admin.isPresent()) {
            return toAdminCO(admin.get());
        } else {
            throw new ApiRuntimeException(ApiErrorCodes.USER_NOT_FOUND, "Admin " + adminId + " not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Chunk<AdminCO> getAdmins(Optional<Integer> page) {

        int pageReceived;

        Integer pageConversion = IterableUtil.getRequestedPage(page);
        pageReceived = IterableUtil.getPageToRequestToRepositoryAndCheck(pageConversion);

        return IterableUtil.toChunk(
                IterableUtil.toStream(this.adminRepository.findAll(PageRequest.of(pageReceived, this.commonProperties.getDefaultChunkSize())))
                        .map(this::toAdminCO)
                        .collect(Collectors.toList()), this.adminRepository.countAll(), pageConversion, this.commonProperties.getDefaultChunkSize());

    }

    @Override
    public AdminCO updateAdmin(Long adminId, AdminForm adminForm) {

        Optional<Admin> admin = this.adminRepository.findById(adminId);

        if (admin.isPresent()) {

            Admin a = admin.get();

            admin.get().setName((adminForm.getName() == null) ? a.getName() : adminForm.getName());
            admin.get().setFirstName((adminForm.getFirstName() == null) ? a.getFirstName() : adminForm.getFirstName());
            admin.get().setSecondName((adminForm.getSecondName() == null) ? a.getSecondName() : adminForm.getSecondName());

            this.adminRepository.save(a);

            return getAdmin(a.getId());

        } else {
            throw new ApiRuntimeException(ApiErrorCodes.USER_NOT_FOUND, "Admin " + adminId + " not found");
        }

    }

    @Override
    public Void deleteAdmin(Long adminId) {

        Optional<Admin> admin = this.adminRepository.findById(adminId);

        if (admin.isPresent()) {
            Admin a = admin.get();
            this.adminRepository.delete(a);
            return null;

        } else {
            throw new ApiRuntimeException(ApiErrorCodes.USER_NOT_FOUND, "Admin " + adminId + " not found");
        }

    }

    public AdminCO toAdminCO(Admin admin) {
        return IterableUtil.to(admin, a -> AdminCO.builder()
                .id(a.getId())
                .name(a.getName())
                .firstName(a.getFirstName())
                .secondName(a.getSecondName())
                .email(a.getEmail())
                .build());
    }


    private void checkEmail(RegisterAdminForm adminForm) {
        boolean existsAdminEmail = userRepository.findByEmail(adminForm.getEmail()) != null;
        if (existsAdminEmail) {
            throw new DuplicateInstanceException(adminForm.getEmail());
        }
    }

    public Admin toRegisterAdmin(RegisterAdminForm adminForm) {
        return IterableUtil.to(adminForm, a -> Admin.builder()
                .email(a.getEmail())
                .password(this.passwordEncoder.encode(a.getPassword()))
                .name(a.getName())
                .firstName(a.getFirstName())
                .secondName(a.getSecondName())
                .build());
    }


}