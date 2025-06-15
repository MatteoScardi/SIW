package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class FishingRod {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String name;
	private String description;
	private String urlImage;
	private String type;
	private String length;
	private String model;
	private String action;
	
	@ManyToMany(mappedBy = "fishingRod")
	private List<FishingPlace> fishingPlace;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<FishingPlace> getFishingPlace() {
		return fishingPlace;
	}

	public void setFishingPlace(List<FishingPlace> fishingPlace) {
		this.fishingPlace = fishingPlace;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, model, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FishingRod other = (FishingRod) obj;
		return Objects.equals(id, other.id) && Objects.equals(model, other.model) && Objects.equals(type, other.type);
	}

}
