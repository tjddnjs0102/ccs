package org.ccs.app.core.share.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseCreatedAndUpdatedDateTime {

    @Column(name = "created_dt")
    @Getter
    protected LocalDateTime createdDateTime;

    @Column(name = "updated_dt")
    @Getter
    protected LocalDateTime updatedDateTime;
}