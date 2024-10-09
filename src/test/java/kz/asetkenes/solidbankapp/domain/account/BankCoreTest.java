package kz.asetkenes.solidbankapp.domain.account;

import kz.asetkenes.solidbankapp.domain.account.model.Account;
import kz.asetkenes.solidbankapp.domain.account.model.AccountType;
import kz.asetkenes.solidbankapp.domain.account.model.CheckingAccount;
import kz.asetkenes.solidbankapp.services.account.AccountCreationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

class BankCoreTest {

    private BankCore bankCore;

    @Test
    public void getAccountTypeByStringCorrectAccountTypeReturnFixed() {
        Account account = new CheckingAccount(
                AccountType.FIXED,
                "1",
                "1",
                0.0,
                false
        );
        AccountType expected = account.getAccountType();
        AccountCreationService mockAccountCreationService = Mockito.mock(AccountCreationService.class);
        Mockito.when(
                mockAccountCreationService.create(
                        any(),
                        anyLong(),
                        any(),
                        anyLong()
                )
        ).thenReturn(account);
        BankCore bankCore = new BankCore(mockAccountCreationService);

        String accountTypeString = "fixed";
        AccountType actual = bankCore.execute(accountTypeString, "1").getAccountType();

        assertEquals(expected, actual);
    }
}