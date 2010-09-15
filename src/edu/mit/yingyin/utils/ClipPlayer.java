package edu.mit.yingyin.utils;

/*
 *	ClipPlayer.java
 *
 *	This file is part of jsresources.org
 */

/*
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
|<---            this code is formatted to fit into 80 columns             --->|
*/

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**	<titleabbrev>ClipPlayer</titleabbrev>
	<title>Playing an audio file multiple times (Clip)</title>

	<formalpara><title>Purpose</title>
	<para>
	Plays a single audio file multiple times in sequence.
	Demonstrates the usage of Java Sound's
	<classname>Clip</classname>.
	</para></formalpara>

	<formalpara><title>Usage</title>
	<cmdsynopsis>
	<command>java ClipPlayer</command>
	<arg choice="plain"><replaceable>audiofile</replaceable></arg>
	<arg choice="plain"><replaceable>#loops</replaceable></arg>
	</cmdsynopsis>
	</formalpara>

	<formalpara><title>Parameters</title>
	<variablelist>
	<varlistentry>
	<term><option><replaceable>audiofile</replaceable></option></term>
	<listitem><para>the name of the
	audio file that should be played</para></listitem>
	</varlistentry>
	<varlistentry>
	<term><option><replaceable>#loops</replaceable></option></term>
	<listitem><para>how often the file should be
	repeated (0 means play once)</para></listitem>
	</varlistentry>
	</variablelist>
	</formalpara>

	<formalpara><title>Bugs, limitations</title>
	<para>Only PCM encoded files are supported. A-law, &mu;-law,
	ADPCM, ogg vorbis, mp3, GSM and other compressed data formats are
	not supported. For a technique of handling these see
	<olink targetdocent="AudioPlayer">AudioPlayer</olink>.</para>
	</formalpara>

	<formalpara><title>Source code</title>
	<para>
	<ulink url="ClipPlayer.java.html">ClipPlayer.java</ulink>
	</para></formalpara>

*/
public class ClipPlayer implements LineListener {
	private Clip clip;
	private DataLine.Info	info;
	private AudioInputStream	audioInputStream;
	private int nLoopCount = 0;

	/*
	 *	The clip will be played nLoopCount + 1 times.
	 */
	public ClipPlayer(File clipFile, int nLoopCount) {
		this.nLoopCount = nLoopCount;
		audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(clipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (audioInputStream != null) {
			AudioFormat	format = audioInputStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
		} else {
			out("ClipPlayer.<init>(): can't get data from file " + 
			    clipFile.getName());
		}
	}

	public void start() {
		try {
			clip = (Clip) AudioSystem.getLine(info);
			clip.addLineListener(this);
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clip.loop(nLoopCount);
	}

	public void update(LineEvent event) {
		if (event.getType().equals(LineEvent.Type.STOP)) {
			clip.close();
		} else if (event.getType().equals(LineEvent.Type.CLOSE)) {
			/*
			 *	There is a bug in the jdk1.3/1.4.
			 *	It prevents correct termination of the VM.
			 *	So we have to exit ourselves.
			 */
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		if (args.length != 2) {
			out("ClipPlayer: usage:");
			out("\tjava ClipPlayer <soundfile> <#loops>");
		} else {
			File	clipFile = new File(args[0]);
			int		nLoopCount = Integer.parseInt(args[1]);
			ClipPlayer	clipPlayer = new ClipPlayer(clipFile, nLoopCount);

			/* In the JDK 5.0, the program would exit if we leave the
			   main loop here. This is because all Java Sound threads
			   have been changed to be daemon threads, not preventing
			   the VM from exiting (this was not the case in 1.4.2 and
			   earlier). So we have to stay in a loop to prevent
			   exiting here.
			*/
			while (true) {
				/* sleep for 1 second. */
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Ignore the exception.
				}
			}
		}
	}

	private static void out(String strMessage) {
		System.out.println(strMessage);
	}
}
