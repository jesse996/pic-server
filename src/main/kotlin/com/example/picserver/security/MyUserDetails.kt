package com.example.picserver.security

import com.example.picserver.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * SpringSecurity需要的用户详情
 */
class MyUserDetails(val user: User, private val permissionList: List<String>) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        //返回当前用户的权限
        return permissionList
            .map { SimpleGrantedAuthority(it) }
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String? {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}