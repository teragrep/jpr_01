package com.teragrep.jpr_01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@EnabledIfSystemProperty(named="runManualMountDetectionTest", matches="true")
public class ManualMountDetectionTest {
    // You should create a new mount on the wanted path to test different behaviours.
    // Example noexec mount that should crash with UnsatisfiedLinkError: mount -t tmpfs -o noexec jpr_01 /tmp/jpr_01/
    // Example default mount: mount -t tmpfs jpr_01 /tmp/jpr_01/
    private static final String tmpDirPath = System.getProperty("tmpDirPath", "/tmp/jpr_01");

    @BeforeAll
    public static void check() {
        Path tmpDir = Paths.get(tmpDirPath);
        if(!Files.isDirectory(tmpDir)) {
            Assertions.fail("Expected filepath <" + tmpDirPath + "> to exist, failing");
        }
    }

    @Test
    public void testMountDetection() {
        System.setProperty("jna.tmpdir", tmpDirPath);
        Assertions.assertDoesNotThrow(JavaPcre::new);
    }
}
