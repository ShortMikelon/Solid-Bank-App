package kz.asetkenes.solidbankapp.data.transactions;

import kz.asetkenes.solidbankapp.domain.transaction.entities.Transaction;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

    @NonNull List<Transaction> findAll();
}
