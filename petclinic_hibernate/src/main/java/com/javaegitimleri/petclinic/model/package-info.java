@org.hibernate.annotations.NamedQuery(name="findPetsByName",query="from Pet p where p.name like :name")

package com.javaegitimleri.petclinic.model;