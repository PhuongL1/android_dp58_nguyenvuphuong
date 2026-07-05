<div align="center">

# 🔐 Android JWT Authentication App

### Android app demo đăng nhập, lưu phiên người dùng và gọi API bảo vệ bằng JWT

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-48B983?style=for-the-badge)
![OkHttp](https://img.shields.io/badge/OkHttp-3E4348?style=for-the-badge)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![REST API](https://img.shields.io/badge/REST%20API-005571?style=for-the-badge)

**Android JWT Authentication App** là ứng dụng Android mô phỏng luồng xác thực người dùng bằng JWT, bao gồm đăng nhập, lưu token, gọi API được bảo vệ, tự động refresh token và đăng xuất bắt buộc khi phiên hết hạn.

</div>

---

## 📌 Tổng quan dự án

Dự án tập trung vào một nghiệp vụ rất phổ biến trong ứng dụng Android thực tế: **xác thực người dùng với REST API bằng JWT**.

Ứng dụng cho phép người dùng đăng nhập, lưu phiên làm việc cục bộ, gọi các API yêu cầu xác thực và tự động xử lý khi `Access Token` hết hạn.

Luồng xử lý chính:

```text
Login
→ Save Access Token / Refresh Token
→ Call Protected API
→ Access Token expired
→ Refresh Token
→ Retry original request
→ Refresh Token expired
→ Clear session
→ Force Logout
```

---

## 🎯 Mục tiêu dự án

Dự án được xây dựng nhằm luyện tập và mô phỏng các kỹ thuật quan trọng khi làm app Android có kết nối backend:

- Gọi API đăng nhập bằng Retrofit.
- Lưu phiên đăng nhập bằng SharedPreferences.
- Gắn `Authorization: Bearer <token>` tự động khi gọi API bảo vệ.
- Xử lý token hết hạn bằng Refresh Token.
- Retry request ban đầu sau khi refresh token thành công.
- Buộc người dùng đăng nhập lại khi Refresh Token hết hạn.
- Hiển thị dữ liệu từ Protected API sau khi xác thực thành công.

---

## ✨ Chức năng chính

### 🔑 Login

- Giao diện đăng nhập bằng XML Layout.
- Nhập email và password.
- Validate dữ liệu đầu vào.
- Gọi Login API bằng Retrofit.
- Nhận thông tin người dùng, Access Token và Refresh Token.

### 💾 Session Management

- Lưu thông tin đăng nhập bằng SharedPreferences.
- Lưu:
  - Access Token
  - Refresh Token
  - User information
- Kiểm tra phiên người dùng khi mở app.
- Clear session khi đăng xuất hoặc token không còn hợp lệ.

### 🛡️ Protected API

- Gọi API yêu cầu xác thực sau khi đăng nhập.
- Tự động gắn Bearer Token vào request thông qua OkHttp Interceptor.
- Hiển thị dữ liệu người dùng/sản phẩm từ API sau khi xác thực thành công.

### 🔄 Refresh Token Flow

- Phát hiện Access Token hết hạn.
- Gọi Refresh Token API để lấy Access Token mới.
- Lưu token mới vào SharedPreferences.
- Retry lại request ban đầu.
- Nếu Refresh Token hết hạn, clear session và điều hướng về Login Screen.

### 🚪 Force Logout

- Khi Refresh Token không còn hợp lệ:
  - Xóa token đã lưu.
  - Xóa thông tin phiên.
  - Chuyển người dùng về màn hình đăng nhập.

---

## 🧭 Luồng màn hình

| Màn hình | Mục đích |
|---|---|
| Login Screen | Nhập email/password và đăng nhập |
| User/Product List Screen | Hiển thị dữ liệu từ Protected API sau khi đăng nhập |

Luồng tổng quát:

```text
Login Screen
→ Login API
→ Save Token
→ User/Product List Screen
→ Protected API
→ Refresh Token nếu cần
→ Force Logout nếu phiên hết hạn
```

---

## 🧱 Công nghệ sử dụng

| Nhóm | Công nghệ |
|---|---|
| Ngôn ngữ | Java |
| UI | XML Layout |
| API Client | Retrofit |
| HTTP Client | OkHttp |
| Local Storage | SharedPreferences |
| Authentication | JWT Authentication |
| API Style | REST API |
| Version Control | Git + GitHub |

---

## 🧩 Kiến trúc xử lý token

### Request bình thường

```text
App
→ OkHttp Interceptor
→ Attach Authorization Header
→ Protected API
→ Response Success
```

Header mẫu:

```text
Authorization: Bearer <access_token>
```

### Khi Access Token hết hạn

```text
Protected API returns 401
→ Call Refresh Token API
→ Save new Access Token
→ Retry original request
→ Return response to UI
```

### Khi Refresh Token hết hạn

```text
Refresh Token API failed
→ Clear SharedPreferences
→ Force Logout
→ Navigate to Login Screen
```

---

## 📌 Repository

Source code: [android_dp58_nguyenvuphuong](https://github.com/PhuongL1/android_dp58_nguyenvuphuong)

---

## 👨‍💻 Author

**Phuong Nguyen**

GitHub: [@PhuongL1](https://github.com/PhuongL1)

---

<div align="center">

### 🔐 Android JWT Authentication App — Login, Token, Protected API, Refresh Flow.

</div>
