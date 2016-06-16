package com.techjar.vivecraftforge.asm;

import java.util.ArrayList;
import java.util.List;

import com.techjar.vivecraftforge.asm.handler.*;
import com.techjar.vivecraftforge.util.VivecraftForgeLog;

import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {
	private static final List<ASMClassHandler> asmHandlers = new ArrayList<ASMClassHandler>();
	static {
		asmHandlers.add(new HandlerEnableTeleporting());
		asmHandlers.add(new HandlerHackForgeChannelName());
	}
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		for (ASMClassHandler handler : asmHandlers) {
			ClassTuple tuple = handler.getDesiredClass();
			if (name.equals(tuple.classNameObf)) {
				VivecraftForgeLog.info("Patching class: " + name + " (" + tuple.className + ")");
				return handler.patchClass(bytes, true);
			} else if (name.equals(tuple.className)) {
				VivecraftForgeLog.info("Patching class: " + name);
				return handler.patchClass(bytes, false);
			}
		}
		return bytes;
	}
}
