package vn.iotstar.service;

import vn.iotstar.util.XMail_24110187;

public class MailService_24110187 {

    public void sendOtp(String toEmail, String fullname, String otp) {
        String subject = "🎬 PolyTube - Mã OTP kích hoạt tài khoản";
        String html = """
            <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;
                        border:1px solid #ddd;border-radius:8px;overflow:hidden">
                <div style="background:#1e293b;color:#fff;padding:16px;text-align:center">
                    <h2 style="margin:0">🎬 PolyTube</h2>
                </div>
                <div style="padding:24px">
                    <p>Xin chào <strong>%s</strong>,</p>
                    <p>Mã OTP kích hoạt tài khoản của bạn là:</p>
                    <div style="text-align:center;margin:24px 0">
                        <span style="font-size:32px;font-weight:bold;letter-spacing:8px;
                                     background:#f1f5f9;padding:12px 24px;border-radius:6px;
                                     color:#0f172a">%s</span>
                    </div>
                    <p>Mã có hiệu lực trong <strong>5 phút</strong>.</p>
                </div>
                <div style="background:#f8fafc;padding:12px;text-align:center;
                            color:#64748b;font-size:13px">
                    © 2026 PolyTube - Bài kiểm tra giữa kỳ
                </div>
            </div>
            """.formatted(fullname, otp);
        XMail_24110187.send(toEmail, subject, html);
    }

    public String generateOtp() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }
}