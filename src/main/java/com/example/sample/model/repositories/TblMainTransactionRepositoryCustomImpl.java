package com.example.sample.model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.sample.model.TblMainTransaction;
import com.example.sample.pojo.TransactionHistoryFilter;
@Repository
public class TblMainTransactionRepositoryCustomImpl implements TblMainTransactionRepositoryCustom {
	private static final String date = "dte";
	private static final String partya = "partya";
	//private static final String partyb = "partyb";
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<TblMainTransaction> findTransactionsRequests(TransactionHistoryFilter filter) throws Exception {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<TblMainTransaction> requestCriteria = builder.createQuery(TblMainTransaction.class);
		final int page = 1;
		final int batch = 20;
		final Root<TblMainTransaction> requestRoot = requestCriteria.from(TblMainTransaction.class);
		Predicate criteria = builder.conjunction();
		if (filter.getPhone() != null) {
			criteria = builder.and(criteria, builder.equal(requestRoot.get(partya), filter.getPhone()));
		}
		if (filter.getStart_date() != null && filter.getEnd_date() != null) {
			
			// criteria = builder.and(criteria,
			criteria = builder.and(criteria,
					builder.between(requestRoot.get(date), filter.getStart_date(), filter.getEnd_date()));
		}
		requestCriteria.select(requestRoot).where(criteria).orderBy(builder.desc(requestRoot.get(date)));
		return entityManager.createQuery(requestCriteria).setFirstResult(page * batch).setMaxResults(batch)
				.getResultList();
	}

}
