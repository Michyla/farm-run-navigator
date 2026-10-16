package com.farmrunnav;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Farm Run Navigator"
)
public class FarmRunNavPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private FarmRunNavConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Farm Run Navigator started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Farm Run Navigator stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Farm Run Navigator says " + config.greeting(), null);
		}
	}

	@Provides
	FarmRunNavConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FarmRunNavConfig.class);
	}
}
