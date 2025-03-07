package kr.co.dysnt.framework.core.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class TmsUser implements UserDetails {
	private String systemCd = "";
	private String userId = "";
	private String empNo = "";
	private String nmKor = "";
	private String nmChn = "";
	private String nmEng = "";
	private String email = "";
	private String dptCd = "";
	private String dptNm = "";
	private String telno = "";
	private String mobileNo = "";
	private String rsofCd = "";
	private String rsofNm = "";
	private String plbsCd = "";
	private String pwd = "";
	private String pwdChngDt = "";
	private String gender = "";
	private String etcoDt = "";
	private String useYn = "";
	private String isAdmin = "";
	private String adminNm = "";
	private String telNo = "";

	private String erpDptCd = "";
	private String erpDptNm = "";
	private String erpSapCd = "";

	private String loginResult;

	private String role;

	private List<String> roles = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "TmsUser{" +
				"systemCd='" + systemCd + '\'' +
				", userId='" + userId + '\'' +
				", empNo='" + empNo + '\'' +
				", nmKor='" + nmKor + '\'' +
				", email='" + email + '\'' +
				", dptCd='" + dptCd + '\'' +
				", dptNm='" + dptNm + '\'' +
				", telNo='" + telNo + '\'' +
				", mobileNo='" + mobileNo + '\'' +
				", pwd='" + pwd + '\'' + // 보안 문제로 비밀번호는 제외하는 것이 좋음
				", isAdmin='" + isAdmin + '\'' +
				", loginResult='" + loginResult + '\'' +
				", roles=" + roles +
				'}';
	}

	@Override
	public String getPassword() {
		return pwd;
	}

	@Override
	public String getUsername() {
		return userId;
	}

	/**
	 * 미접속사용자정지기준일수 넘어선 유저
	 */
	@Override
	public boolean isAccountNonExpired() {
		return "0005".equals(this.loginResult) ? false : true;
	}

	/**
	 * 차단 유저
	 */
	@Override
	public boolean isAccountNonLocked() {
		return "0007".equals(this.loginResult) ? false : true;
	}

	/**
	 * 비밀번호교체 기준일수 넘어선 유저
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return "0003".equals(this.loginResult) ? false : true;
	}

	/**
	 * 차단여부
	 */
	@Override
	public boolean isEnabled() {
		return "0002".equals(this.loginResult) ? false : true;
	}

	public boolean isLoginFailNumbered() {
		return "0004".equals(this.loginResult) ? false : true;
	}

	public boolean isLoginLocked() {
		return "0009".equals(this.loginResult) ? false : true;
	}

	public String getAdminNm() {
		return adminNm;
	}

	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSystemCd() {
		return systemCd;
	}

	public void setSystemCd(String systemCd) {
		this.systemCd = systemCd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getNmKor() {
		return nmKor;
	}

	public void setNmKor(String nmKor) {
		this.nmKor = nmKor;
	}

	public String getNmChn() {
		return nmChn;
	}

	public void setNmChn(String nmChn) {
		this.nmChn = nmChn;
	}

	public String getNmEng() {
		return nmEng;
	}

	public void setNmEng(String nmEng) {
		this.nmEng = nmEng;
	}

	public String getDptCd() {
		return dptCd;
	}

	public void setDptCd(String dptCd) {
		this.dptCd = dptCd;
	}

	public String getDptNm() {
		return dptNm;
	}

	public void setDptNm(String dptNm) {
		this.dptNm = dptNm;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRsofCd() {
		return rsofCd;
	}

	public void setRsofCd(String rsofCd) {
		this.rsofCd = rsofCd;
	}

	public String getRsofNm() {
		return rsofNm;
	}

	public void setRsofNm(String rsofNm) {
		this.rsofNm = rsofNm;
	}

	public String getPlbsCd() {
		return plbsCd;
	}

	public void setPlbsCd(String plbsCd) {
		this.plbsCd = plbsCd;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdChngDt() {
		return pwdChngDt;
	}

	public void setPwdChngDt(String pwdChngDt) {
		this.pwdChngDt = pwdChngDt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEtcoDt() {
		return etcoDt;
	}

	public void setEtcoDt(String etcoDt) {
		this.etcoDt = etcoDt;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public void setErpDptCd(String erpDptCd) {
		this.erpDptCd = erpDptCd;
	}

	public void setErpDptNm(String erpDptNm) {
		this.erpDptNm = erpDptNm;
	}

	public void setErpSapCd(String erpSapCd) {
		this.erpSapCd = erpSapCd;
	}

	public String getErpDptCd() {
		return this.erpDptCd;
	}

	public String getErpDptNm() {
		return this.erpDptNm;
	}

	public String getErpSapCd() {
		return this.erpSapCd;
	}
}
