package kz.asetkenes.solidbankapp.domain.transaction;

import jakarta.transaction.Transactional;
import kz.asetkenes.solidbankapp.domain.account.entities.Account;
import kz.asetkenes.solidbankapp.services.account.AccountDepositService;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import kz.asetkenes.solidbankapp.services.account.AccountWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferUseCase {

    @Autowired
    private AccountDepositService depositService;

    @Autowired
    private AccountWithdrawService withdrawService;

    @Autowired
    private AccountListingService listingService;

    @Transactional
    public void execute(
            Account withdrawAccount,
            Account depositAccount,
            double amount
    ) {
        withdrawService.withdraw(amount, withdrawAccount);

        depositService.deposit(depositAccount, amount);
    }

}
