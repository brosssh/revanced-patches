package app.revanced.extension.flo.premium;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class UnlockPremiumPatch {
    public static Set<String> getPatchedFeatures() {
        Set<String> v0 = new HashSet<>();
        v0.add("library");
        v0.add("virtual_assistant");
        v0.add("ttc_sex_widget");
        v0.add("symptom_patterns");
        v0.add("stories");
        v0.add("pregnancy_details");
        v0.add("report_for_doctor");
        v0.add("cycle_trends_widget");
        v0.add("symptom_checker");
        v0.add("track");
        v0.add("premium");
        v0.add("premium_partnership");
        return v0;
    }
}
