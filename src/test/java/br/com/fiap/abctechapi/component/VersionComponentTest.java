package br.com.fiap.abctechapi.component;

import br.com.fiap.abctechapi.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VersionComponentTest {

    private VersionComponent versionComponent;
    private Properties properties;

    @BeforeEach
    public void before(){
        properties = mock(Properties.class);
        versionComponent = new VersionComponent(properties);
    }

    @Test
    public void whenInvokeGetVersionMethodThenReturnCorrectVerion(){
        String expectedVersion = "1.0.0";
        when(properties.getProperty(eq("build.version"))).thenReturn(expectedVersion);

        assertEquals(expectedVersion, versionComponent.getVersion());
    }

    @Test
    public void whenInvokeGetProjectNameMethodThenReturnCorrectProjectName(){
        String expectedProjectName = "abcTechApi";

        when(properties.getProperty(eq("build.name"))).thenReturn(expectedProjectName);

        assertEquals(expectedProjectName, versionComponent.getProjectName());
    }

    @Test
    public void whenInvokeGetProjectVersionMethodThenReturnCorrectProjectVersion(){
        String expectedProjectName = "abcTechApi";
        String expectedVersion = "1.0.0";

        when(properties.getProperty(eq("build.version"))).thenReturn(expectedVersion);
        when(properties.getProperty(eq("build.name"))).thenReturn(expectedProjectName);

        Version version = versionComponent.getProjectVersion();

        assertEquals(expectedProjectName, version.getProjectName());
        assertEquals(expectedVersion, version.getVersion());
    }

    @Test
    public void whenInvokeLoadMethodWithExceptionThenPrintStackCorrectly() throws IOException {
        IOException ioException = mock(IOException.class);

        doThrow(ioException).when(properties).load(any(InputStream.class));

        new VersionComponent(properties);

        verify(ioException).printStackTrace();
    }

    @Test
    public void test(){
        assertDoesNotThrow(() -> new VersionComponent());
    }
}