package org.ccs.app.core.common.model;

import lombok.Getter;
import lombok.ToString;
import org.ccs.app.core.common.domain.Menu;
import org.ccs.app.core.share.model.EntityBuildable;

import java.util.Objects;

@Getter @ToString
public class RootMenuCreateParameter implements EntityBuildable<Menu> {
    private String name;

    public RootMenuCreateParameter(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public Menu toEntity() {
        return Menu.builder().name(name).build();
    }
}
