import java.util.*;

class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Step 1: Initialize
        for (List<String> acc : accounts) {
            String name = acc.get(0);

            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                parent.putIfAbsent(email, email);
                emailToName.put(email, name);

                // Union first email with others
                if (i > 1) {
                    union(parent, acc.get(1), email);
                }
            }
        }

        // Step 2: Group emails by root
        Map<String, List<String>> groups = new HashMap<>();

        for (String email : parent.keySet()) {
            String root = find(parent, email);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        // Step 3: Build result
        List<List<String>> result = new ArrayList<>();

        for (String root : groups.keySet()) {
            List<String> emails = groups.get(root);
            Collections.sort(emails);

            List<String> account = new ArrayList<>();
            account.add(emailToName.get(root)); // name
            account.addAll(emails);

            result.add(account);
        }

        return result;
    }

    private String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x))); // path compression
        }
        return parent.get(x);
    }

    private void union(Map<String, String> parent, String a, String b) {
        String rootA = find(parent, a);
        String rootB = find(parent, b);

        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
        }
    }
}