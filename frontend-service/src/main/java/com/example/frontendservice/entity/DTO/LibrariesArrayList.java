package com.example.frontendservice.entity.DTO;

import com.example.frontendservice.entity.Library;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibrariesArrayList {
    private ArrayList<Library> libraries;
}
