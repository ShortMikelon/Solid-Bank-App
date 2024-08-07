package kz.asetkenes.solidbankapp.domain.transaction;

import jakarta.transaction.Transactional;
import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.domain.transaction.model.TransactionType;
import kz.asetkenes.solidbankapp.exception.InsufficientFundsException;
import kz.asetkenes.solidbankapp.exception.NegativeAmountException;
import kz.asetkenes.solidbankapp.exception.WithdrawNotAllowedException;
import kz.asetkenes.solidbankapp.services.account.AccountDepositService;
import kz.asetkenes.solidbankapp.services.account.AccountListingService;
import kz.asetkenes.solidbankapp.services.account.AccountWithdrawService;
import kz.asetkenes.solidbankapp.services.transactions.ITransactionCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferUseCase {

    private final AccountDepositService depositService;

    private final AccountWithdrawService withdrawService;

    private final ITransactionCreateService transactionCreateService;

    @Transactional
    public void execute(
            Account withdrawAccount,
            Account depositAccount,
            double amount
    ) {
        if (amount < 0) throw new NegativeAmountException("");
        if (amount > withdrawAccount.getBalance()) throw new InsufficientFundsException("");
        if (!withdrawAccount.isWithdrawAllowed()) throw new WithdrawNotAllowedException();

        withdrawService.withdraw(amount, withdrawAccount);
        transactionCreateService.save(
                TransactionType.WITHDRAW,
                withdrawAccount.getId(),
                withdrawAccount.getClientId(),
                amount
        );

        depositService.deposit(depositAccount, amount);
        transactionCreateService.save(
                TransactionType.DEPOSIT,
                depositAccount.getId(),
                depositAccount.getClientId(),
                amount
        );
    }

}
