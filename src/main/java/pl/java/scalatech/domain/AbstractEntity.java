/*
 * Copyright 2016 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.java.scalatech.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostUpdate;
import javax.persistence.Version;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EqualsAndHashCode(of="id")
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1764429777262538648L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

  
    @Version
    @Getter
    private Long version;
  
}
