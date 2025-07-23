package Esport_Website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Webhook {
    public String gateway;
    public String transactionDate;
    public String accountNumber;
    public String subAccount;

    public String transferType;
    public double transferAmount;
    public double accumulated;

    public String code;
    public String content;
    public String referenceCode;
    public String description;
}
