package com.editor.wysiwygEngine.game;

import com.editor.gui.main.test;
import com.editor.wysiwygEngine.base.core.Game;
import com.editor.wysiwygEngine.base.core.GameObject;
import com.editor.wysiwygEngine.planetParser.GameCreator;

public class GeneratedGame extends Game
{
	public void init()
	{
		for(GameObject go : new GameCreator(test.filename).createGame())
		{
			addObject(go);
		}
	}
}
