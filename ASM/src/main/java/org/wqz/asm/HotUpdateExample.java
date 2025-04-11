package org.wqz.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.springframework.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

// 要热更新的类
class TargetClass {
    public void printMessage() {
        System.out.println("Original message");
    }
}

// 自定义类加载器
class CustomClassLoader extends ClassLoader {
    public Class<?> defineClassFromBytes(String name, byte[] bytes) {
        return defineClass(name, bytes, 0, bytes.length);
    }
}

// ASM 类修改器
class ClassModifier extends ClassVisitor {
    public ClassModifier(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("printMessage")) {
            mv = new MethodModifier(mv);
        }
        return mv;
    }
}

// ASM 方法修改器
class MethodModifier extends MethodVisitor {
    public MethodModifier(MethodVisitor mv) {
        super(Opcodes.ASM9, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Updated message");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(Opcodes.RETURN);
    }
}

public class HotUpdateExample {
    public static void main(String[] args) throws Exception {
        // 原始类调用
        TargetClass target = new TargetClass();
        target.printMessage();

        // 使用 ASM 修改类的字节码
        ClassReader cr = new ClassReader(TargetClass.class.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassModifier cm = new ClassModifier(cw);
        cr.accept(cm, 0);
        byte[] modifiedBytes = cw.toByteArray();

        // 保存修改后的字节码到文件（可选）
        try (FileOutputStream fos = new FileOutputStream("TargetClass_modified.class")) {
            fos.write(modifiedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用自定义类加载器加载修改后的类
        CustomClassLoader classLoader = new CustomClassLoader();
        Class<?> modifiedClass = classLoader.defineClassFromBytes(TargetClass.class.getName(), modifiedBytes);
        Object modifiedInstance = modifiedClass.getDeclaredConstructor().newInstance();

        // 调用修改后的方法
        Method method = modifiedClass.getMethod("printMessage");
        method.invoke(modifiedInstance);
    }
}    