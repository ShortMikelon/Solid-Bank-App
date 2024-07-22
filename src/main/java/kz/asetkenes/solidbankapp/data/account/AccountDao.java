package kz.asetkenes.solidbankapp.data.account;

import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {

    List<Account> findByClientId(String clientId);

    List<Account> findByClientIdAndAccountType(String clientId, AccountType accountType);

    Account findByIdAndClientId(String id, String clientId);

}