package com.project.moviesapi;

import com.project.moviesapi.service.FileServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceImplTest {

    @InjectMocks
    private FileServiceImpl fileService;

    private final String testUploadPath = "src/test/resources/";
    private final String testFileName = "panda-2.png";
    private final String fileContent = "Test file content";

    @BeforeEach
    void setUp() throws IOException {
        // Create test directory before each test
        Files.createDirectories(Paths.get(testUploadPath));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up test directory after each test
        // FileSystemUtils.deleteRecursively(Paths.get(testUploadPath));
        // the following code will delete files and not directories
        Files.walk(Paths.get(testUploadPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    // Tests directory creation
    @Test
    void uploadFile_shouldCreateDirectory_whenNotExists() throws IOException {
        // Arrange
        String newDirectoryPath = testUploadPath + "/testposters";
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "image/png",
                fileContent.getBytes()
        );

        // Ensure directory doesn't exist before test
        Files.deleteIfExists(Paths.get(newDirectoryPath));

        // Act
        String resultFileName = fileService.uploadFile(newDirectoryPath, mockFile);

        // Assert
        assertEquals(testFileName, resultFileName);
        assertTrue(Files.exists(Paths.get(newDirectoryPath, testFileName)));
    }

    // Tests successful file upload
    @Test
    void uploadFile_shouldSaveFile_whenValidFileProvided() throws IOException {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "image/png",
                fileContent.getBytes()
        );

        // Act
        String resultFileName = fileService.uploadFile(testUploadPath, mockFile);

        // Assert
        assertEquals(testFileName, resultFileName);
        assertTrue(Files.exists(Paths.get(testUploadPath, testFileName)));
    }

    // Tests error handling
    @Test
    void getResourceFile_shouldThrowException_whenFileNotExists() {
        // Arrange
        String nonExistentFile = "non-existent-file.png";

        // Act & Assert
        assertThrows(FileNotFoundException.class, () -> {
            fileService.getResourceFile(testUploadPath, nonExistentFile);
        });
    }

    // Tests file retrieval
    @Test
    void getResourceFile_shouldReturnInputStream_whenFileExists() throws IOException {
        // Arrange - first upload a test file
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                testFileName,
                "image/png",
                fileContent.getBytes()
        );
        fileService.uploadFile(testUploadPath, mockFile);

        // Act
        InputStream inputStream = fileService.getResourceFile(testUploadPath, testFileName);

        // Assert
        assertNotNull(inputStream);
        inputStream.close();
    }
}
