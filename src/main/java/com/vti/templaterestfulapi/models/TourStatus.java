package com.vti.templaterestfulapi.models;

public class TourStatus {
    public static final int TOUR_OPEN = 0; // Co nghia la vao dat duoc tour
    public static final int TOUR_DOING = 3; // Co nghia la tour dang dien ra
    public static final int TOUR_CLOSE = 1; // Co nghia la khong dat duoc tour  Da dien ra - co khach hang
    public static final int TOUR_CANCEL = 2; // Co nghia la tour bi HUY
}
