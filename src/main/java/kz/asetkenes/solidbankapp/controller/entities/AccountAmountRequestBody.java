package kz.asetkenes.solidbankapp.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountAmountRequestBody {
    private double amount;
}
