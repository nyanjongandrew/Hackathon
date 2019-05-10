package com.example.sample.model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.sample.model.Moviez;
import com.example.sample.pojo.MovieFilter;

public class MoviezRepositoryCustomImpl implements MoviezRepositoryCustom {
	private static final String watched = "watched";
	private static final String regdate = "regDate";
	// private static final String serviceId = "serviceId";
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Moviez> findMovies(MovieFilter filter) throws Exception {
		// final int page = filter.getPage() > 0 ? filter.getPage() - 1 : 0;
		// final int batch = filter.getBatch() <= 0 ? 10 : filter.getBatch();
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Moviez> requestCriteria = builder.createQuery(Moviez.class);

		final Root<Moviez> requestRoot = requestCriteria.from(Moviez.class);
		Predicate criteria = builder.conjunction();
		if (filter.getWatched() != null) {
			criteria = builder.and(criteria,
					builder.equal(requestRoot.get(watched), filter.getWatched() == true ? 1 : 0));
		}

		requestCriteria.select(requestRoot).where(criteria).orderBy(builder.desc(requestRoot.get(regdate)));
		return entityManager.createQuery(requestCriteria).getResultList();
	}

	@Override
	public Long countServiceMovies(MovieFilter filter) throws Exception {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> clienteCriteria = builder.createQuery(Long.class);
		final Root<Moviez> serviceRequestRoot = clienteCriteria.from(Moviez.class);
		Predicate criteria = builder.conjunction();
		// final String status = "status";
		if (filter.getWatched() != null) {

			criteria = builder.and(criteria,
					builder.equal(serviceRequestRoot.get(watched), filter.getWatched() == true ? 1 : 0));
		}
		clienteCriteria.select(builder.count(serviceRequestRoot)).where(criteria);
		return entityManager.createQuery(clienteCriteria).getSingleResult();
	}

}
