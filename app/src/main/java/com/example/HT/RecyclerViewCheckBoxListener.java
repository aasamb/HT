package com.example.HT;

public interface RecyclerViewCheckBoxListener {
    // Interface to update data structures in classes needed according to the changes in
    // the Lutemons checked. This works, this is completely different to the attempts to update
    // RecyclerView according to the changes in the actual locations of the Lutemons.
    void onCheckboxStateChanged(int position, boolean isChecked);
}
