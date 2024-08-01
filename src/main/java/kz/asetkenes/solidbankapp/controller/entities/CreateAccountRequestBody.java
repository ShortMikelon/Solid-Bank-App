package kz.asetkenes.solidbankapp.controller.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestBody {
    @NonNull private String accountType;
}
