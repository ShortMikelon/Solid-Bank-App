package kz.asetkenes.solidbankapp.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepositWithdrawRequest {
    private double amount;
}
