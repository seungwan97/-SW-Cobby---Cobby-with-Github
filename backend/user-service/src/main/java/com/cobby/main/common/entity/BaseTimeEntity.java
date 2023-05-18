package com.cobby.main.common.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/entity/BaseTimeEntity.java
=======
import org.springframework.data.annotation.LastModifiedDate;
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/entity/BaseTimeEntity.java
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
<<<<<<< HEAD:backend/main/src/main/java/com/cobby/main/common/entity/BaseTimeEntity.java
import lombok.Getter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseTimeEntity {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

}
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@EntityListeners(value = AuditingEntityListener.class) // Auditing 기능 포함
@MappedSuperclass	// JPA Entity 클래스들이 이 클래스를 상속할 경우 필드들도 칼럼으로 인식하게 함.
public class BaseTimeEntity extends CreatedTimeEntity {

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime lastModifiedAt; // 생성 시간


	@Override
	public String toString() {
		return "BaseTimeEntity{" +
			"createdAt=" + super.getCreatedAt() +
			", LastModifiedAt=" + lastModifiedAt +
			'}';
	}
}
>>>>>>> 5289a1722e99d1d71f86eeba31ce77e8b65da365:backend/user-service/src/main/java/com/cobby/main/common/entity/BaseTimeEntity.java
