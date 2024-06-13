package com.trioshop.utils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomTransactionService {
    private PlatformTransactionManager transactionManager;

    // 트랜잭션 시작
    public TransactionStatus startTransaction() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionManager.getTransaction(def);
    }

    // 커밋
    public void commitTransaction(TransactionStatus status) {
        transactionManager.commit(status);
    }

    // 롤백
    public void rollbackTransaction(TransactionStatus status) {
        transactionManager.rollback(status);
    }
}