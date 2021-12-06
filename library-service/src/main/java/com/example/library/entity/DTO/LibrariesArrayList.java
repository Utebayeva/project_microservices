package com.example.library.entity.DTO;

import com.example.library.entity.Library;
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
