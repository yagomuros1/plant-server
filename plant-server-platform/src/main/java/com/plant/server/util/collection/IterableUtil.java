package com.plant.server.util.collection;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.plant.server.business.services.exceptions.BadRequestException;

public class IterableUtil {

	public static final int CHUNK_SIZE = 3;
	private static final int FIRST_PAGE_JPA_PARAM = 0;
	private static final int FIRST_PAGE_API_PARAM = 1;

	
	public static <T> Stream<T> toStream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}
	public static <T> Chunk<T> toChunk(List<T> items, long total, Integer currentPage, int chunkSize) {
		boolean hasMore = ( (currentPage-1)*chunkSize+items.size() ) < total;
		return Chunk.<T>builder()
				.items(items)
				.total(total)
				.currentPage(currentPage)
				.chunkSize(chunkSize)
				.hasMore(hasMore)
				.build();
	}
	
	public static int getPageToRequestToRepositoryAndCheck(Integer page) {
		int pageToRequestToRepository = page.intValue()-1;
		if (pageToRequestToRepository<FIRST_PAGE_JPA_PARAM) {
			throw new BadRequestException(page, "page not found");
		}
		return pageToRequestToRepository;
	}
	public static int getRequestedPage(Optional<Integer> pageOpt) {
		if (pageOpt.isPresent()) {
			int page = pageOpt.get().intValue();
			if (page<FIRST_PAGE_API_PARAM) {
				throw new BadRequestException(page, "page not found");
			}
			return page;
		} else {
			return FIRST_PAGE_API_PARAM;
		}
	}

	public static <T> Collector<T, ?, T> toSingleton() {
	    return Collectors.collectingAndThen(
	            Collectors.toList(),
	            list -> {
	                if (list.size() == 0) {
	                    return null;
	                } else if (list.size() == 1) {
	                	return list.get(0);
	                } else {
	                	throw new IllegalStateException();
	                }
	            }
	    );
	}
	
	@FunctionalInterface
	public interface ITo<T1, T2> {
	    public T2 transform(T1 element);
	}
	public static <T1, T2> T2 to(T1 e, ITo<T1, T2> fn) {
		return e==null?null:fn.transform(e);
	}
	public static <T1, T2> List<T2> map(List<T1> l, ITo<T1, T2> fn) {
		return l==null?null:l.stream().map(e -> to(e, fn)).collect(Collectors.toList());
	}
	public static <T1, T2> Set<T2> mapSet(Set<T1> l, ITo<T1, T2> fn) {
		return l==null?null:l.stream().map(e -> to(e, fn)).collect(Collectors.toSet());
	}
}
