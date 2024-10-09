package kz.asetkenes.solidbankapp.domain.account.dto;

import kz.asetkenes.solidbankapp.domain.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountResponse {
    private String id;
    private AccountType accountType;
    private String clientId;
    private double balance;
    private boolean withdrawAllowed;
}
