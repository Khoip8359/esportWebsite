package Esport_Website.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRequest {
	@NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;
	
	@NotBlank(message = "Xác nhận mật khẩu không được để trống")
    private String oldPassword;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự trở lên")
    private String newPassword;

    @NotBlank(message = "Xác nhận mật khẩu không được để trống")
    private String confirmPassword;
}
