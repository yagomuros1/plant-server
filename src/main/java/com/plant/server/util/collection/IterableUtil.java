package com.plant.server.util.collection;

import java.util.*;
import java.util.function.Function;
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
	public static <T> T toSingleton(Collection<T> c) {
		return c.stream().collect(toSingleton());
	}

	@FunctionalInterface
	public interface ITo<T1, T2> {
		T2 transform(T1 element);
	}
	@FunctionalInterface
	public interface IAccomplish<T1> {
		boolean accomplish(T1 element);
	}
	public static <T1, T2> T2 to(T1 e, ITo<T1, T2> fn) {
		return e==null?null:fn.transform(e);
	}
	public static <T1> boolean accomplish(T1 e, IAccomplish<T1> fn) {
		return fn.accomplish(e);
	}
	public static <T1, T2> List<T2> map(List<T1> l, ITo<T1, T2> fn) {
		return l==null?null:l.stream().map(e -> to(e, fn)).collect(Collectors.toList());
	}
	public static <T1, T2> Set<T2> mapSet(Set<T1> l, ITo<T1, T2> fn) {
		return l==null?null:l.stream().map(e -> to(e, fn)).collect(Collectors.toSet());
	}
	public static <T1> List<T1> filter(List<T1> l, IAccomplish<T1> fn) {
		return l==null?null:l.stream().filter(e -> accomplish(e, fn)).collect(Collectors.toList());
	}
	public static <T1> Set<T1> filter(Set<T1> l, IAccomplish<T1> fn) {
		return l==null?null:l.stream().filter(e -> accomplish(e, fn)).collect(Collectors.toSet());
	}
	public static <T1> List<T1> sort(List<T1> list, Comparator<T1> comparator) {
		return list==null?null:list.stream().sorted(comparator).collect(Collectors.toList());
	}


	public static <T> List<T> getAll(Function<Integer, Chunk<T>> getChunkFunction) throws Exception {
		int page = 1;
		List<T> list = new ArrayList<>();
		Chunk<T> chunk = getChunkFunction.apply(page);
		while (chunk.getItems().size()>0) {
			list.addAll(chunk.getItems());
			if (chunk.isHasMore()) {
				page++;
				chunk = getChunkFunction.apply(page);
			} else {
				break;
			}
		}
		return list;
	}

}
