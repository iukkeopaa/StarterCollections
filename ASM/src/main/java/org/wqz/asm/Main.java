package org.wqz.asm;

import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;

// 目标类，我们将对其方法进行增强
class TargetClass1 {
    public void targetMethod() {
        System.out.println("Inside targetMethod");
    }
}

// 自定义 ClassVisitor，用于修改字节码
class LoggingClassVisitor extends ClassVisitor {
    public LoggingClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (!name.equals("<init>") && mv != null) {
            mv = new LoggingMethodVisitor(mv);
        }
        return mv;
    }
}

// 自定义 MethodVisitor，用于修改方法字节码
class LoggingMethodVisitor extends MethodVisitor {
    public LoggingMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM9, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Before method execution");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("After method execution");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        // 创建 ClassReader 读取目标类的字节码
        ClassReader cr = new ClassReader(TargetClass1.class.getName());
        // 创建 ClassWriter 用于生成新的字节码
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        // 创建自定义的 ClassVisitor
        ClassVisitor cv = new LoggingClassVisitor(cw);
        // 开始处理字节码
        cr.accept(cv, 0);
        // 获取增强后的字节码
        byte[] enhancedClassBytes = cw.toByteArray();

        // 将增强后的字节码保存到文件（可选）
        try (FileOutputStream fos = new FileOutputStream("EnhancedTargetClass1.class")) {
            fos.write(enhancedClassBytes);
        }

        // 这里只是简单示例，实际中你可能需要使用自定义类加载器加载增强后的类
        // 然后调用其方法进行测试
    }
}    