package com.draco18s.industry.client.gui;

import com.draco18s.industry.entities.TileEntityWoodenHopper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiContainerExtHopper extends GuiContainer {
	TileEntityHopper tileEntity;
	Container container;
	private static ResourceLocation WOODEN_HOPPER_GUI_TEXTURE;

	public GuiContainerExtHopper(Container inventorySlotsIn, TileEntityHopper te) {
		super(inventorySlotsIn);
		tileEntity = te;
		WOODEN_HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
		this.ySize = 133;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(new TextComponentTranslation(tileEntity.getName()).getUnformattedText(), 8, 6, 4210752);
		this.fontRenderer.drawString(new TextComponentTranslation("container.inventory").getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

		this.mc.getTextureManager().bindTexture(WOODEN_HOPPER_GUI_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
