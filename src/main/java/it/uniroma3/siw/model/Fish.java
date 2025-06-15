package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import com.sun.istack.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Fish {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String scientificName;
	
	private String description;
	private String urlImage;
	private String season;
	
	@ManyToMany
	private List<Bait> bait;
	
	@ManyToMany(mappedBy = "fish")
	private List<FishingPlace> fishingPlace;
	
	//private CatchLog;
	
	
	
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

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
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

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public List<Bait> getBait() {
		return bait;
	}

	public void setBait(List<Bait> bait) {
		this.bait = bait;
	}

	public List<FishingPlace> getFishingPlace() {
		return fishingPlace;
	}

	public void setFishingPlace(List<FishingPlace> fishingPlace) {
		this.fishingPlace = fishingPlace;
	}

	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, scientificName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fish other = (Fish) obj;
		return Objects.equals(id, other.id) && Objects.equals(scientificName, other.scientificName);
	}
	
	
}
