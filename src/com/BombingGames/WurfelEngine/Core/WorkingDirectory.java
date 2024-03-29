/*
 * If this software is used for a game the official „Wurfel Engine“ logo or its name must be visible in an intro screen or main menu.
 * 
 * Copyright 2014 Benedikt Vogler.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * * Neither the name of Benedikt Vogler nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.BombingGames.WurfelEngine.Core;

import java.io.File;

/**
 * A class which helps getting OS specific information
 * @author Martin Brunokowsky
 */
public class WorkingDirectory {
    /**
     *
     * @param applicationName
     * @return
     */
    public static File getWorkingDirectory(final String applicationName) {
        String userHome = System.getProperty("user.home", ".");
        File workingDirectory;
        switch (getPlatform()) {
        case linux:
        case solaris:
                workingDirectory = new File(userHome, '.' + applicationName + '/');
                break;
        case windows:
                String applicationData = System.getenv("APPDATA");
                if (applicationData != null)
                        workingDirectory = new File(applicationData, applicationName + '/');
                else
                        workingDirectory = new File(userHome, '.' + applicationName + '/');
                break;
        case macos:
                workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
                break;
        default:
                workingDirectory = new File(userHome, applicationName + '/');
        }
        if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs()))
                throw new RuntimeException("The working directory could not be created: " + workingDirectory);
        return workingDirectory;
    }

    private static OS getPlatform() {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("win"))
                    return OS.windows;
            if (osName.contains("mac"))
                    return OS.macos;
            if (osName.contains("solaris"))
                    return OS.solaris;
            if (osName.contains("sunos"))
                    return OS.solaris;
            if (osName.contains("linux"))
                    return OS.linux;
            if (osName.contains("unix"))
                    return OS.linux;
            return OS.unknown;
    }

    private static enum OS {
            linux, solaris, windows, macos, unknown;
    }
}