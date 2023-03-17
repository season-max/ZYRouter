package com.zhangyue.ireader.compiler.processor;

import static com.zhangyue.ireader.compiler.utils.Constant.MODULE_NAME;

import com.zhangyue.ireader.compiler.utils.Constant;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import jdk.nashorn.internal.ir.FunctionNode;

public abstract class BaseProcessor extends AbstractProcessor {

    /**
     * 工具类
     */
    protected Filer filer;
    protected Types typeUtils;
    protected Messager messager;
    protected Elements elementUtils;

    /**
     * 模块名称
     */
    protected String moduleName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        Map<String, String> options = processingEnv.getOptions();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            if (MODULE_NAME.equals(entry.getKey())) {
                moduleName = entry.getValue();
                messager.printMessage(Diagnostic.Kind.NOTE, "module name:" + moduleName);
            }
        }
    }

    @Override
    public Set<String> getSupportedOptions() {
        return new HashSet<String>() {{
            add(MODULE_NAME);
        }};
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
