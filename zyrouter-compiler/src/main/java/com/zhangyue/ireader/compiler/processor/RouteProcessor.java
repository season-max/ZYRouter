package com.zhangyue.ireader.compiler.processor;

import static com.zhangyue.ireader.compiler.utils.Constant.ANNOTATION_ROUTE;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.zhangyue.ireader.annotations.Route;
import com.zhangyue.ireader.compiler.utils.Constant;
import com.zhangyue.ireader.module.RouteMeta;
import com.zhangyue.ireader.module.RouteType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedAnnotationTypes(ANNOTATION_ROUTE)
public class RouteProcessor extends BaseProcessor {

    private final Map<String, RouteMeta> mRouteMap = new HashMap<>();


    /**
     * {@inheritDoc}
     * 生成目标代码
     * public class ARouter$$Group$$m2 implements IRouteGroup {
     *
     * @Override public void loadInto(Map<String, RouteMeta> atlas) {
     * atlas.put("/module/2", RouteMeta.build(RouteType.ACTIVITY, TestModule2Activity.class, "/module/2", "m2", null, -1, -2147483648));
     * }
     * }
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Route.class);

        mRouteMap.clear();

        if (elementsAnnotatedWith == null || elementsAnnotatedWith.isEmpty()) {
            return false;
        }
        if (moduleName == null || moduleName.isEmpty()) {
            return false;
        }

        TypeMirror type_Activity = elementUtils.getTypeElement(Constant.ANDROID_ACTIVITY).asType();
        TypeMirror type_Fragment = elementUtils.getTypeElement(Constant.ANDROID_FRAGMENT).asType();

        for (Element element : elementsAnnotatedWith) {
            if (element.getKind() == ElementKind.CLASS) {
                final String path = element.getAnnotation(Route.class).path();
                messager.printMessage(Diagnostic.Kind.NOTE, "route path:" + path);
                TypeMirror tm = element.asType();
                if (typeUtils.isSubtype(tm, type_Activity)) { // activity
                    RouteMeta meta = new RouteMeta();
                    meta.setPath(path);
                    meta.setRouteType(RouteType.ACTIVITY);
                    meta.setElement(element);
                    mRouteMap.put(path, meta);
                } else if (typeUtils.isSubtype(tm, type_Fragment)) {
                    RouteMeta meta = new RouteMeta();
                    meta.setPath(path);
                    meta.setRouteType(RouteType.FRAGMENT);
                    meta.setElement(element);
                    mRouteMap.put(path, meta);
                }
            }
        }

        if (mRouteMap.isEmpty()) {
            messager.printMessage(Diagnostic.Kind.ERROR, "route map is null~");
        }

        //****************************************构建代码块*****************************************************

        // parameterType Map<String,Route>
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(Route.class)
        );

        // parameter Map<String, RouteMeta> atlas
        ParameterSpec parameterSpec = ParameterSpec.builder(parameterizedTypeName, "atlas")
                .build();


        return true;
    }
}
