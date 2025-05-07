package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotels {
	@Id
	@Positive(message = "Hotel ID must be a positive number")
	@NotNull
    private int hotelId;

    @NotBlank(message = "Hotel name cannot be empty")
    @Size(min =2 , max = 100, message = "Hotel name must be between 2 and 100 characters")
    private String hotelName;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    @Positive(message = "Manager ID must be a positive number")
    @NotNull
    private int managerId;
    
    private int roomId;

    private String amenities;
    
    private int roomCount;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private int rating;

}
