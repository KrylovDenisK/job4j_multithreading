package ru.job4j.cuncurrent.synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
@ThreadSafe
public class ParseFile {
    @GuardedBy("this")
    private File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized void setFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        StringBuffer output = new StringBuffer();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            input.lines().forEach(output::append);
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        StringBuffer output = new StringBuffer();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter o = new PrintWriter(file)) {
            o.write(content);
        }
    }
}
