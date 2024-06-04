package org.api.util;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class NewsAPIUtil {
    /**
     * Generates Java POJO class object from URL having JSON contents
     * @param url   - URL which contains JSON contents
     * @param className - JSON main class name or main URL name
     * @param packageName - Applicable package name as per project structure
     * @throws IOException  - Throws IOException while generating Java class from JSON
     */
    public void generateJavaClassFromJSON(URL url, String className, String packageName) throws IOException {
        JCodeModel jcodeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }
            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(jcodeModel, className, packageName, url);
        String basePath = "src/main/java";
        File outputPojoDirectory = new File(basePath);
        jcodeModel.build(outputPojoDirectory);
    }
}
