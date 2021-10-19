package maxver90.blog.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    // GrantedAuthority - интерфейс, который описывает роли и привилегии пользователя в Spring Security.

    // Под ролью понимается общая принадлежность к группе, например админ или модератор и т.д. Роль в Spring Security
    // всегда начинаться с префикса ROLE_ после чего должно идти название роли (желательно в верхнем регистре) Т.е.
    // роль "админ" в Spring Security должна иметь название ROLE_ADMIN и т.д.

    // Под привилегией понимается возможность пользователя совершать отдельно взятое действие вне зависимости
    // от его роли, например удалять комментарии к статьям, но при этом эта привилегия не делает его полноценным
    // модератором. В Spring Security привилегия на удаление комментариев могла бы иметь название deleteComments.

    private final String authority;

    private final Boolean isRole;


    public GrantedAuthorityImpl(String authority, boolean isRole) {
        this.authority = authority;
        this.isRole = isRole;
    }

    @Override
    public String getAuthority() {
        // Если это роль, то необходимо добавить префикс ROLE_
        if (isRole){
            return "ROLE_" + authority.toUpperCase();
        }
        return authority;
    }
}
