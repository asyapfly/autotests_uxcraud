package org.uxcrowd.education.utils;

import java.util.Random;

public class RandomEmailGenerator {

    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com"};
        String[] names = {"john", "jane", "mary", "bob", "alice", "david", "susan", "tom", "kate", "peter"};
        String[] extensions = {"com", "net", "org"};

        Random random = new Random();
        String name = names[random.nextInt(names.length)];
        String domain = domains[random.nextInt(domains.length)];
        String extension = extensions[random.nextInt(extensions.length)];

        int number = random.nextInt(10000);

        return name + number + "@" + domain + "." + extension;
    }
}