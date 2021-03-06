/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nanan.main;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Object used to report key and button events.
 * <p>
 * Each key press is described by a sequence of key events.  A key press
 * starts with a key event with {@link #ACTION_DOWN}.  If the key is held
 * sufficiently long that it repeats, then the initial down is followed
 * additional key events with {@link #ACTION_DOWN} and a non-zero value for
 * {@link #getRepeatCount()}.  The last key event is a {@link #ACTION_UP}
 * for the key up.  If the key press is canceled, the key up event will have the
 * {@link #FLAG_CANCELED} flag set.
 * </p><p>
 * Key events are generally accompanied by a key code ({@link #getKeyCode()}),
 * scan code ({@link #getScanCode()}) and meta state ({@link #getMetaState()}).
 * Key code constants are defined in this class.  Scan code constants are raw
 * device-specific codes obtained from the OS and so are not generally meaningful
 * to applications unless interpreted using the {@link KeyCharacterMap}.
 * Meta states describe the pressed state of key modifiers
 * such as {@link #META_SHIFT_ON} or {@link #META_ALT_ON}.
 * </p><p>
 * Key codes typically correspond one-to-one with individual keys on an input device.
 * Many keys and key combinations serve quite different functions on different
 * input devices so care must be taken when interpreting them.  Always use the
 * {@link KeyCharacterMap} associated with the input device when mapping keys
 * to characters.  Be aware that there may be multiple key input devices active
 * at the same time and each will have its own key character map.
 * </p><p>
 * As soft input methods can use multiple and inventive ways of inputting text,
 * there is no guarantee that any key press on a soft keyboard will generate a key
 * event: this is left to the IME's discretion, and in fact sending such events is
 * discouraged.  You should never rely on receiving KeyEvents for any key on a soft
 * input method.  In particular, the default software keyboard will never send any
 * key event to any application targetting Jelly Bean or later, and will only send
 * events for some presses of the delete and return keys to applications targetting
 * Ice Cream Sandwich or earlier.  Be aware that other software input methods may
 * never send key events regardless of the version.  Consider using editor actions
 * like {@link android.view.inputmethod.EditorInfo#IME_ACTION_DONE} if you need
 * specific interaction with the software keyboard, as it gives more visibility to
 * the user as to how your application will react to key presses.
 * </p><p>
 * When interacting with an IME, the framework may deliver key events
 * with the special action {@link #ACTION_MULTIPLE} that either specifies
 * that single repeated key code or a sequence of characters to insert.
 * </p><p>
 * In general, the framework cannot guarantee that the key events it delivers
 * to a view always constitute complete key sequences since some events may be dropped
 * or modified by containing views before they are delivered.  The view implementation
 * should be prepared to handle {@link #FLAG_CANCELED} and should tolerate anomalous
 * situations such as receiving a new {@link #ACTION_DOWN} without first having
 * received an {@link #ACTION_UP} for the prior key press.
 * </p><p>
 * Refer to {@link InputDevice} for more information about how different kinds of
 * input devices and sources represent keys and buttons.
 * </p>
 */
public class AndroidKeyEvent {
    /** Key code constant: Unknown key code. */
    public static final int KEYCODE_UNKNOWN         = 0;
    /** Key code constant: Soft Left key.
     * Usually situated below the display on phones and used as a multi-function
     * feature key for selecting a software defined function shown on the bottom left
     * of the display. */
    public static final int KEYCODE_SOFT_LEFT       = 1;
    /** Key code constant: Soft Right key.
     * Usually situated below the display on phones and used as a multi-function
     * feature key for selecting a software defined function shown on the bottom right
     * of the display. */
    public static final int KEYCODE_SOFT_RIGHT      = 2;
    /** Key code constant: Home key.
     * This key is handled by the framework and is never delivered to applications. */
    public static final int KEYCODE_HOME            = 3;
    /** Key code constant: Back key. */
    public static final int KEYCODE_BACK            = 4;
    /** Key code constant: Call key. */
    public static final int KEYCODE_CALL            = 5;
    /** Key code constant: End Call key. */
    public static final int KEYCODE_ENDCALL         = 6;
    /** Key code constant: '0' key. */
    public static final int KEYCODE_0               = 7;
    /** Key code constant: '1' key. */
    public static final int KEYCODE_1               = 8;
    /** Key code constant: '2' key. */
    public static final int KEYCODE_2               = 9;
    /** Key code constant: '3' key. */
    public static final int KEYCODE_3               = 10;
    /** Key code constant: '4' key. */
    public static final int KEYCODE_4               = 11;
    /** Key code constant: '5' key. */
    public static final int KEYCODE_5               = 12;
    /** Key code constant: '6' key. */
    public static final int KEYCODE_6               = 13;
    /** Key code constant: '7' key. */
    public static final int KEYCODE_7               = 14;
    /** Key code constant: '8' key. */
    public static final int KEYCODE_8               = 15;
    /** Key code constant: '9' key. */
    public static final int KEYCODE_9               = 16;
    /** Key code constant: '*' key. */
    public static final int KEYCODE_STAR            = 17;
    /** Key code constant: '#' key. */
    public static final int KEYCODE_POUND           = 18;
    /** Key code constant: Directional Pad Up key.
     * May also be synthesized from trackball motions. */
    public static final int KEYCODE_DPAD_UP         = 19;
    /** Key code constant: Directional Pad Down key.
     * May also be synthesized from trackball motions. */
    public static final int KEYCODE_DPAD_DOWN       = 20;
    /** Key code constant: Directional Pad Left key.
     * May also be synthesized from trackball motions. */
    public static final int KEYCODE_DPAD_LEFT       = 21;
    /** Key code constant: Directional Pad Right key.
     * May also be synthesized from trackball motions. */
    public static final int KEYCODE_DPAD_RIGHT      = 22;
    /** Key code constant: Directional Pad Center key.
     * May also be synthesized from trackball motions. */
    public static final int KEYCODE_DPAD_CENTER     = 23;
    /** Key code constant: Volume Up key.
     * Adjusts the speaker volume up. */
    public static final int KEYCODE_VOLUME_UP       = 24;
    /** Key code constant: Volume Down key.
     * Adjusts the speaker volume down. */
    public static final int KEYCODE_VOLUME_DOWN     = 25;
    /** Key code constant: Power key. */
    public static final int KEYCODE_POWER           = 26;
    /** Key code constant: Camera key.
     * Used to launch a camera application or take pictures. */
    public static final int KEYCODE_CAMERA          = 27;
    /** Key code constant: Clear key. */
    public static final int KEYCODE_CLEAR           = 28;
    /** Key code constant: 'A' key. */
    public static final int KEYCODE_A               = 29;
    /** Key code constant: 'B' key. */
    public static final int KEYCODE_B               = 30;
    /** Key code constant: 'C' key. */
    public static final int KEYCODE_C               = 31;
    /** Key code constant: 'D' key. */
    public static final int KEYCODE_D               = 32;
    /** Key code constant: 'E' key. */
    public static final int KEYCODE_E               = 33;
    /** Key code constant: 'F' key. */
    public static final int KEYCODE_F               = 34;
    /** Key code constant: 'G' key. */
    public static final int KEYCODE_G               = 35;
    /** Key code constant: 'H' key. */
    public static final int KEYCODE_H               = 36;
    /** Key code constant: 'I' key. */
    public static final int KEYCODE_I               = 37;
    /** Key code constant: 'J' key. */
    public static final int KEYCODE_J               = 38;
    /** Key code constant: 'K' key. */
    public static final int KEYCODE_K               = 39;
    /** Key code constant: 'L' key. */
    public static final int KEYCODE_L               = 40;
    /** Key code constant: 'M' key. */
    public static final int KEYCODE_M               = 41;
    /** Key code constant: 'N' key. */
    public static final int KEYCODE_N               = 42;
    /** Key code constant: 'O' key. */
    public static final int KEYCODE_O               = 43;
    /** Key code constant: 'P' key. */
    public static final int KEYCODE_P               = 44;
    /** Key code constant: 'Q' key. */
    public static final int KEYCODE_Q               = 45;
    /** Key code constant: 'R' key. */
    public static final int KEYCODE_R               = 46;
    /** Key code constant: 'S' key. */
    public static final int KEYCODE_S               = 47;
    /** Key code constant: 'T' key. */
    public static final int KEYCODE_T               = 48;
    /** Key code constant: 'U' key. */
    public static final int KEYCODE_U               = 49;
    /** Key code constant: 'V' key. */
    public static final int KEYCODE_V               = 50;
    /** Key code constant: 'W' key. */
    public static final int KEYCODE_W               = 51;
    /** Key code constant: 'X' key. */
    public static final int KEYCODE_X               = 52;
    /** Key code constant: 'Y' key. */
    public static final int KEYCODE_Y               = 53;
    /** Key code constant: 'Z' key. */
    public static final int KEYCODE_Z               = 54;
    /** Key code constant: ',' key. */
    public static final int KEYCODE_COMMA           = 55;
    /** Key code constant: '.' key. */
    public static final int KEYCODE_PERIOD          = 56;
    /** Key code constant: Left Alt modifier key. */
    public static final int KEYCODE_ALT_LEFT        = 57;
    /** Key code constant: Right Alt modifier key. */
    public static final int KEYCODE_ALT_RIGHT       = 58;
    /** Key code constant: Left Shift modifier key. */
    public static final int KEYCODE_SHIFT_LEFT      = 59;
    /** Key code constant: Right Shift modifier key. */
    public static final int KEYCODE_SHIFT_RIGHT     = 60;
    /** Key code constant: Tab key. */
    public static final int KEYCODE_TAB             = 61;
    /** Key code constant: Space key. */
    public static final int KEYCODE_SPACE           = 62;
    /** Key code constant: Symbol modifier key.
     * Used to enter alternate symbols. */
    public static final int KEYCODE_SYM             = 63;
    /** Key code constant: Explorer special function key.
     * Used to launch a browser application. */
    public static final int KEYCODE_EXPLORER        = 64;
    /** Key code constant: Envelope special function key.
     * Used to launch a mail application. */
    public static final int KEYCODE_ENVELOPE        = 65;
    /** Key code constant: Enter key. */
    public static final int KEYCODE_ENTER           = 66;
    /** Key code constant: Backspace key.
     * Deletes characters before the insertion point, unlike {@link #KEYCODE_FORWARD_DEL}. */
    public static final int KEYCODE_DEL             = 67;
    /** Key code constant: '`' (backtick) key. */
    public static final int KEYCODE_GRAVE           = 68;
    /** Key code constant: '-'. */
    public static final int KEYCODE_MINUS           = 69;
    /** Key code constant: '=' key. */
    public static final int KEYCODE_EQUALS          = 70;
    /** Key code constant: '[' key. */
    public static final int KEYCODE_LEFT_BRACKET    = 71;
    /** Key code constant: ']' key. */
    public static final int KEYCODE_RIGHT_BRACKET   = 72;
    /** Key code constant: '\' key. */
    public static final int KEYCODE_BACKSLASH       = 73;
    /** Key code constant: ';' key. */
    public static final int KEYCODE_SEMICOLON       = 74;
    /** Key code constant: ''' (apostrophe) key. */
    public static final int KEYCODE_APOSTROPHE      = 75;
    /** Key code constant: '/' key. */
    public static final int KEYCODE_SLASH           = 76;
    /** Key code constant: '@' key. */
    public static final int KEYCODE_AT              = 77;
    /** Key code constant: Number modifier key.
     * Used to enter numeric symbols.
     * This key is not Num Lock; it is more like {@link #KEYCODE_ALT_LEFT} and is
     * interpreted as an ALT key by {@link android.text.method.MetaKeyKeyListener}. */
    public static final int KEYCODE_NUM             = 78;
    /** Key code constant: Headset Hook key.
     * Used to hang up calls and stop media. */
    public static final int KEYCODE_HEADSETHOOK     = 79;
    /** Key code constant: Camera Focus key.
     * Used to focus the camera. */
    public static final int KEYCODE_FOCUS           = 80;   // *Camera* focus
    /** Key code constant: '+' key. */
    public static final int KEYCODE_PLUS            = 81;
    /** Key code constant: Menu key. */
    public static final int KEYCODE_MENU            = 82;
    /** Key code constant: Notification key. */
    public static final int KEYCODE_NOTIFICATION    = 83;
    /** Key code constant: Search key. */
    public static final int KEYCODE_SEARCH          = 84;
    /** Key code constant: Play/Pause media key. */
    public static final int KEYCODE_MEDIA_PLAY_PAUSE= 85;
    /** Key code constant: Stop media key. */
    public static final int KEYCODE_MEDIA_STOP      = 86;
    /** Key code constant: Play Next media key. */
    public static final int KEYCODE_MEDIA_NEXT      = 87;
    /** Key code constant: Play Previous media key. */
    public static final int KEYCODE_MEDIA_PREVIOUS  = 88;
    /** Key code constant: Rewind media key. */
    public static final int KEYCODE_MEDIA_REWIND    = 89;
    /** Key code constant: Fast Forward media key. */
    public static final int KEYCODE_MEDIA_FAST_FORWARD = 90;
    /** Key code constant: Mute key.
     * Mutes the microphone, unlike {@link #KEYCODE_VOLUME_MUTE}. */
    public static final int KEYCODE_MUTE            = 91;
    /** Key code constant: Page Up key. */
    public static final int KEYCODE_PAGE_UP         = 92;
    /** Key code constant: Page Down key. */
    public static final int KEYCODE_PAGE_DOWN       = 93;
    /** Key code constant: Picture Symbols modifier key.
     * Used to switch symbol sets (Emoji, Kao-moji). */
    public static final int KEYCODE_PICTSYMBOLS     = 94;   // switch symbol-sets (Emoji,Kao-moji)
    /** Key code constant: Switch Charset modifier key.
     * Used to switch character sets (Kanji, Katakana). */
    public static final int KEYCODE_SWITCH_CHARSET  = 95;   // switch char-sets (Kanji,Katakana)
    /** Key code constant: A Button key.
     * On a game controller, the A button should be either the button labeled A
     * or the first button on the bottom row of controller buttons. */
    public static final int KEYCODE_BUTTON_A        = 96;
    /** Key code constant: B Button key.
     * On a game controller, the B button should be either the button labeled B
     * or the second button on the bottom row of controller buttons. */
    public static final int KEYCODE_BUTTON_B        = 97;
    /** Key code constant: C Button key.
     * On a game controller, the C button should be either the button labeled C
     * or the third button on the bottom row of controller buttons. */
    public static final int KEYCODE_BUTTON_C        = 98;
    /** Key code constant: X Button key.
     * On a game controller, the X button should be either the button labeled X
     * or the first button on the upper row of controller buttons. */
    public static final int KEYCODE_BUTTON_X        = 99;
    /** Key code constant: Y Button key.
     * On a game controller, the Y button should be either the button labeled Y
     * or the second button on the upper row of controller buttons. */
    public static final int KEYCODE_BUTTON_Y        = 100;
    /** Key code constant: Z Button key.
     * On a game controller, the Z button should be either the button labeled Z
     * or the third button on the upper row of controller buttons. */
    public static final int KEYCODE_BUTTON_Z        = 101;
    /** Key code constant: L1 Button key.
     * On a game controller, the L1 button should be either the button labeled L1 (or L)
     * or the top left trigger button. */
    public static final int KEYCODE_BUTTON_L1       = 102;
    /** Key code constant: R1 Button key.
     * On a game controller, the R1 button should be either the button labeled R1 (or R)
     * or the top right trigger button. */
    public static final int KEYCODE_BUTTON_R1       = 103;
    /** Key code constant: L2 Button key.
     * On a game controller, the L2 button should be either the button labeled L2
     * or the bottom left trigger button. */
    public static final int KEYCODE_BUTTON_L2       = 104;
    /** Key code constant: R2 Button key.
     * On a game controller, the R2 button should be either the button labeled R2
     * or the bottom right trigger button. */
    public static final int KEYCODE_BUTTON_R2       = 105;
    /** Key code constant: Left Thumb Button key.
     * On a game controller, the left thumb button indicates that the left (or only)
     * joystick is pressed. */
    public static final int KEYCODE_BUTTON_THUMBL   = 106;
    /** Key code constant: Right Thumb Button key.
     * On a game controller, the right thumb button indicates that the right
     * joystick is pressed. */
    public static final int KEYCODE_BUTTON_THUMBR   = 107;
    /** Key code constant: Start Button key.
     * On a game controller, the button labeled Start. */
    public static final int KEYCODE_BUTTON_START    = 108;
    /** Key code constant: Select Button key.
     * On a game controller, the button labeled Select. */
    public static final int KEYCODE_BUTTON_SELECT   = 109;
    /** Key code constant: Mode Button key.
     * On a game controller, the button labeled Mode. */
    public static final int KEYCODE_BUTTON_MODE     = 110;
    /** Key code constant: Escape key. */
    public static final int KEYCODE_ESCAPE          = 111;
    /** Key code constant: Forward Delete key.
     * Deletes characters ahead of the insertion point, unlike {@link #KEYCODE_DEL}. */
    public static final int KEYCODE_FORWARD_DEL     = 112;
    /** Key code constant: Left Control modifier key. */
    public static final int KEYCODE_CTRL_LEFT       = 113;
    /** Key code constant: Right Control modifier key. */
    public static final int KEYCODE_CTRL_RIGHT      = 114;
    /** Key code constant: Caps Lock key. */
    public static final int KEYCODE_CAPS_LOCK       = 115;
    /** Key code constant: Scroll Lock key. */
    public static final int KEYCODE_SCROLL_LOCK     = 116;
    /** Key code constant: Left Meta modifier key. */
    public static final int KEYCODE_META_LEFT       = 117;
    /** Key code constant: Right Meta modifier key. */
    public static final int KEYCODE_META_RIGHT      = 118;
    /** Key code constant: Function modifier key. */
    public static final int KEYCODE_FUNCTION        = 119;
    /** Key code constant: System Request / Print Screen key. */
    public static final int KEYCODE_SYSRQ           = 120;
    /** Key code constant: Break / Pause key. */
    public static final int KEYCODE_BREAK           = 121;
    /** Key code constant: Home Movement key.
     * Used for scrolling or moving the cursor around to the start of a line
     * or to the top of a list. */
    public static final int KEYCODE_MOVE_HOME       = 122;
    /** Key code constant: End Movement key.
     * Used for scrolling or moving the cursor around to the end of a line
     * or to the bottom of a list. */
    public static final int KEYCODE_MOVE_END        = 123;
    /** Key code constant: Insert key.
     * Toggles insert / overwrite edit mode. */
    public static final int KEYCODE_INSERT          = 124;
    /** Key code constant: Forward key.
     * Navigates forward in the history stack.  Complement of {@link #KEYCODE_BACK}. */
    public static final int KEYCODE_FORWARD         = 125;
    /** Key code constant: Play media key. */
    public static final int KEYCODE_MEDIA_PLAY      = 126;
    /** Key code constant: Pause media key. */
    public static final int KEYCODE_MEDIA_PAUSE     = 127;
    /** Key code constant: Close media key.
     * May be used to close a CD tray, for example. */
    public static final int KEYCODE_MEDIA_CLOSE     = 128;
    /** Key code constant: Eject media key.
     * May be used to eject a CD tray, for example. */
    public static final int KEYCODE_MEDIA_EJECT     = 129;
    /** Key code constant: Record media key. */
    public static final int KEYCODE_MEDIA_RECORD    = 130;
    /** Key code constant: F1 key. */
    public static final int KEYCODE_F1              = 131;
    /** Key code constant: F2 key. */
    public static final int KEYCODE_F2              = 132;
    /** Key code constant: F3 key. */
    public static final int KEYCODE_F3              = 133;
    /** Key code constant: F4 key. */
    public static final int KEYCODE_F4              = 134;
    /** Key code constant: F5 key. */
    public static final int KEYCODE_F5              = 135;
    /** Key code constant: F6 key. */
    public static final int KEYCODE_F6              = 136;
    /** Key code constant: F7 key. */
    public static final int KEYCODE_F7              = 137;
    /** Key code constant: F8 key. */
    public static final int KEYCODE_F8              = 138;
    /** Key code constant: F9 key. */
    public static final int KEYCODE_F9              = 139;
    /** Key code constant: F10 key. */
    public static final int KEYCODE_F10             = 140;
    /** Key code constant: F11 key. */
    public static final int KEYCODE_F11             = 141;
    /** Key code constant: F12 key. */
    public static final int KEYCODE_F12             = 142;
    /** Key code constant: Num Lock key.
     * This is the Num Lock key; it is different from {@link #KEYCODE_NUM}.
     * This key alters the behavior of other keys on the numeric keypad. */
    public static final int KEYCODE_NUM_LOCK        = 143;
    /** Key code constant: Numeric keypad '0' key. */
    public static final int KEYCODE_NUMPAD_0        = 144;
    /** Key code constant: Numeric keypad '1' key. */
    public static final int KEYCODE_NUMPAD_1        = 145;
    /** Key code constant: Numeric keypad '2' key. */
    public static final int KEYCODE_NUMPAD_2        = 146;
    /** Key code constant: Numeric keypad '3' key. */
    public static final int KEYCODE_NUMPAD_3        = 147;
    /** Key code constant: Numeric keypad '4' key. */
    public static final int KEYCODE_NUMPAD_4        = 148;
    /** Key code constant: Numeric keypad '5' key. */
    public static final int KEYCODE_NUMPAD_5        = 149;
    /** Key code constant: Numeric keypad '6' key. */
    public static final int KEYCODE_NUMPAD_6        = 150;
    /** Key code constant: Numeric keypad '7' key. */
    public static final int KEYCODE_NUMPAD_7        = 151;
    /** Key code constant: Numeric keypad '8' key. */
    public static final int KEYCODE_NUMPAD_8        = 152;
    /** Key code constant: Numeric keypad '9' key. */
    public static final int KEYCODE_NUMPAD_9        = 153;
    /** Key code constant: Numeric keypad '/' key (for division). */
    public static final int KEYCODE_NUMPAD_DIVIDE   = 154;
    /** Key code constant: Numeric keypad '*' key (for multiplication). */
    public static final int KEYCODE_NUMPAD_MULTIPLY = 155;
    /** Key code constant: Numeric keypad '-' key (for subtraction). */
    public static final int KEYCODE_NUMPAD_SUBTRACT = 156;
    /** Key code constant: Numeric keypad '+' key (for addition). */
    public static final int KEYCODE_NUMPAD_ADD      = 157;
    /** Key code constant: Numeric keypad '.' key (for decimals or digit grouping). */
    public static final int KEYCODE_NUMPAD_DOT      = 158;
    /** Key code constant: Numeric keypad ',' key (for decimals or digit grouping). */
    public static final int KEYCODE_NUMPAD_COMMA    = 159;
    /** Key code constant: Numeric keypad Enter key. */
    public static final int KEYCODE_NUMPAD_ENTER    = 160;
    /** Key code constant: Numeric keypad '=' key. */
    public static final int KEYCODE_NUMPAD_EQUALS   = 161;
    /** Key code constant: Numeric keypad '(' key. */
    public static final int KEYCODE_NUMPAD_LEFT_PAREN = 162;
    /** Key code constant: Numeric keypad ')' key. */
    public static final int KEYCODE_NUMPAD_RIGHT_PAREN = 163;
    /** Key code constant: Volume Mute key.
     * Mutes the speaker, unlike {@link #KEYCODE_MUTE}.
     * This key should normally be implemented as a toggle such that the first press
     * mutes the speaker and the second press restores the original volume. */
    public static final int KEYCODE_VOLUME_MUTE     = 164;
    /** Key code constant: Info key.
     * Common on TV remotes to show additional information related to what is
     * currently being viewed. */
    public static final int KEYCODE_INFO            = 165;
    /** Key code constant: Channel up key.
     * On TV remotes, increments the television channel. */
    public static final int KEYCODE_CHANNEL_UP      = 166;
    /** Key code constant: Channel down key.
     * On TV remotes, decrements the television channel. */
    public static final int KEYCODE_CHANNEL_DOWN    = 167;
    /** Key code constant: Zoom in key. */
    public static final int KEYCODE_ZOOM_IN         = 168;
    /** Key code constant: Zoom out key. */
    public static final int KEYCODE_ZOOM_OUT        = 169;
    /** Key code constant: TV key.
     * On TV remotes, switches to viewing live TV. */
    public static final int KEYCODE_TV              = 170;
    /** Key code constant: Window key.
     * On TV remotes, toggles picture-in-picture mode or other windowing functions. */
    public static final int KEYCODE_WINDOW          = 171;
    /** Key code constant: Guide key.
     * On TV remotes, shows a programming guide. */
    public static final int KEYCODE_GUIDE           = 172;
    /** Key code constant: DVR key.
     * On some TV remotes, switches to a DVR mode for recorded shows. */
    public static final int KEYCODE_DVR             = 173;
    /** Key code constant: Bookmark key.
     * On some TV remotes, bookmarks content or web pages. */
    public static final int KEYCODE_BOOKMARK        = 174;
    /** Key code constant: Toggle captions key.
     * Switches the mode for closed-captioning text, for example during television shows. */
    public static final int KEYCODE_CAPTIONS        = 175;
    /** Key code constant: Settings key.
     * Starts the system settings activity. */
    public static final int KEYCODE_SETTINGS        = 176;
    /** Key code constant: TV power key.
     * On TV remotes, toggles the power on a television screen. */
    public static final int KEYCODE_TV_POWER        = 177;
    /** Key code constant: TV input key.
     * On TV remotes, switches the input on a television screen. */
    public static final int KEYCODE_TV_INPUT        = 178;
    /** Key code constant: Set-top-box power key.
     * On TV remotes, toggles the power on an external Set-top-box. */
    public static final int KEYCODE_STB_POWER       = 179;
    /** Key code constant: Set-top-box input key.
     * On TV remotes, switches the input mode on an external Set-top-box. */
    public static final int KEYCODE_STB_INPUT       = 180;
    /** Key code constant: A/V Receiver power key.
     * On TV remotes, toggles the power on an external A/V Receiver. */
    public static final int KEYCODE_AVR_POWER       = 181;
    /** Key code constant: A/V Receiver input key.
     * On TV remotes, switches the input mode on an external A/V Receiver. */
    public static final int KEYCODE_AVR_INPUT       = 182;
    /** Key code constant: Red "programmable" key.
     * On TV remotes, acts as a contextual/programmable key. */
    public static final int KEYCODE_PROG_RED        = 183;
    /** Key code constant: Green "programmable" key.
     * On TV remotes, actsas a contextual/programmable key. */
    public static final int KEYCODE_PROG_GREEN      = 184;
    /** Key code constant: Yellow "programmable" key.
     * On TV remotes, acts as a contextual/programmable key. */
    public static final int KEYCODE_PROG_YELLOW     = 185;
    /** Key code constant: Blue "programmable" key.
     * On TV remotes, acts as a contextual/programmable key. */
    public static final int KEYCODE_PROG_BLUE       = 186;
    /** Key code constant: App switch key.
     * Should bring up the application switcher dialog. */
    public static final int KEYCODE_APP_SWITCH      = 187;
    /** Key code constant: Generic Game Pad Button #1.*/
    public static final int KEYCODE_BUTTON_1        = 188;
    /** Key code constant: Generic Game Pad Button #2.*/
    public static final int KEYCODE_BUTTON_2        = 189;
    /** Key code constant: Generic Game Pad Button #3.*/
    public static final int KEYCODE_BUTTON_3        = 190;
    /** Key code constant: Generic Game Pad Button #4.*/
    public static final int KEYCODE_BUTTON_4        = 191;
    /** Key code constant: Generic Game Pad Button #5.*/
    public static final int KEYCODE_BUTTON_5        = 192;
    /** Key code constant: Generic Game Pad Button #6.*/
    public static final int KEYCODE_BUTTON_6        = 193;
    /** Key code constant: Generic Game Pad Button #7.*/
    public static final int KEYCODE_BUTTON_7        = 194;
    /** Key code constant: Generic Game Pad Button #8.*/
    public static final int KEYCODE_BUTTON_8        = 195;
    /** Key code constant: Generic Game Pad Button #9.*/
    public static final int KEYCODE_BUTTON_9        = 196;
    /** Key code constant: Generic Game Pad Button #10.*/
    public static final int KEYCODE_BUTTON_10       = 197;
    /** Key code constant: Generic Game Pad Button #11.*/
    public static final int KEYCODE_BUTTON_11       = 198;
    /** Key code constant: Generic Game Pad Button #12.*/
    public static final int KEYCODE_BUTTON_12       = 199;
    /** Key code constant: Generic Game Pad Button #13.*/
    public static final int KEYCODE_BUTTON_13       = 200;
    /** Key code constant: Generic Game Pad Button #14.*/
    public static final int KEYCODE_BUTTON_14       = 201;
    /** Key code constant: Generic Game Pad Button #15.*/
    public static final int KEYCODE_BUTTON_15       = 202;
    /** Key code constant: Generic Game Pad Button #16.*/
    public static final int KEYCODE_BUTTON_16       = 203;
    /** Key code constant: Language Switch key.
     * Toggles the current input language such as switching between English and Japanese on
     * a QWERTY keyboard.  On some devices, the same function may be performed by
     * pressing Shift+Spacebar. */
    public static final int KEYCODE_LANGUAGE_SWITCH = 204;
    /** Key code constant: Manner Mode key.
     * Toggles silent or vibrate mode on and off to make the device behave more politely
     * in certain settings such as on a crowded train.  On some devices, the key may only
     * operate when long-pressed. */
    public static final int KEYCODE_MANNER_MODE     = 205;
    /** Key code constant: 3D Mode key.
     * Toggles the display between 2D and 3D mode. */
    public static final int KEYCODE_3D_MODE         = 206;
    /** Key code constant: Contacts special function key.
     * Used to launch an address book application. */
    public static final int KEYCODE_CONTACTS        = 207;
    /** Key code constant: Calendar special function key.
     * Used to launch a calendar application. */
    public static final int KEYCODE_CALENDAR        = 208;
    /** Key code constant: Music special function key.
     * Used to launch a music player application. */
    public static final int KEYCODE_MUSIC           = 209;
    /** Key code constant: Calculator special function key.
     * Used to launch a calculator application. */
    public static final int KEYCODE_CALCULATOR      = 210;
    /** Key code constant: Japanese full-width / half-width key. */
    public static final int KEYCODE_ZENKAKU_HANKAKU = 211;
    /** Key code constant: Japanese alphanumeric key. */
    public static final int KEYCODE_EISU            = 212;
    /** Key code constant: Japanese non-conversion key. */
    public static final int KEYCODE_MUHENKAN        = 213;
    /** Key code constant: Japanese conversion key. */
    public static final int KEYCODE_HENKAN          = 214;
    /** Key code constant: Japanese katakana / hiragana key. */
    public static final int KEYCODE_KATAKANA_HIRAGANA = 215;
    /** Key code constant: Japanese Yen key. */
    public static final int KEYCODE_YEN             = 216;
    /** Key code constant: Japanese Ro key. */
    public static final int KEYCODE_RO              = 217;
    /** Key code constant: Japanese kana key. */
    public static final int KEYCODE_KANA            = 218;
    /** Key code constant: Assist key.
     * Launches the global assist activity.  Not delivered to applications. */
    public static final int KEYCODE_ASSIST          = 219;
    /** Key code constant: Brightness Down key.
     * Adjusts the screen brightness down. */
    public static final int KEYCODE_BRIGHTNESS_DOWN = 220;
    /** Key code constant: Brightness Up key.
     * Adjusts the screen brightness up. */
    public static final int KEYCODE_BRIGHTNESS_UP   = 221;
    /** Key code constant: Audio Track key.
     * Switches the audio tracks. */
    public static final int KEYCODE_MEDIA_AUDIO_TRACK = 222;
    /** Key code constant: Sleep key.
     * Puts the device to sleep.  Behaves somewhat like {@link #KEYCODE_POWER} but it
     * has no effect if the device is already asleep. */
    public static final int KEYCODE_SLEEP           = 223;
    /** Key code constant: Wakeup key.
     * Wakes up the device.  Behaves somewhat like {@link #KEYCODE_POWER} but it
     * has no effect if the device is already awake. */
    public static final int KEYCODE_WAKEUP          = 224;
    /** Key code constant: Pairing key.
     * Initiates peripheral pairing mode. Useful for pairing remote control
     * devices or game controllers, especially if no other input mode is
     * available. */
    public static final int KEYCODE_PAIRING         = 225;
    /** Key code constant: Media Top Menu key.
     * Goes to the top of media menu. */
    public static final int KEYCODE_MEDIA_TOP_MENU  = 226;
    /** Key code constant: '11' key. */
    public static final int KEYCODE_11              = 227;
    /** Key code constant: '12' key. */
    public static final int KEYCODE_12              = 228;
    /** Key code constant: Last Channel key.
     * Goes to the last viewed channel. */
    public static final int KEYCODE_LAST_CHANNEL    = 229;
    /** Key code constant: TV data service key.
     * Displays data services like weather, sports. */
    public static final int KEYCODE_TV_DATA_SERVICE = 230;
    /** Key code constant: Voice Assist key.
     * Launches the global voice assist activity. Not delivered to applications. */
    public static final int KEYCODE_VOICE_ASSIST = 231;
    /** Key code constant: Radio key.
     * Toggles TV service / Radio service. */
    public static final int KEYCODE_TV_RADIO_SERVICE = 232;
    /** Key code constant: Teletext key.
     * Displays Teletext service. */
    public static final int KEYCODE_TV_TELETEXT = 233;
    /** Key code constant: Number entry key.
     * Initiates to enter multi-digit channel nubmber when each digit key is assigned
     * for selecting separate channel. Corresponds to Number Entry Mode (0x1D) of CEC
     * User Control Code. */
    public static final int KEYCODE_TV_NUMBER_ENTRY = 234;
    /** Key code constant: Analog Terrestrial key.
     * Switches to analog terrestrial broadcast service. */
    public static final int KEYCODE_TV_TERRESTRIAL_ANALOG = 235;
    /** Key code constant: Digital Terrestrial key.
     * Switches to digital terrestrial broadcast service. */
    public static final int KEYCODE_TV_TERRESTRIAL_DIGITAL = 236;
    /** Key code constant: Satellite key.
     * Switches to digital satellite broadcast service. */
    public static final int KEYCODE_TV_SATELLITE = 237;
    /** Key code constant: BS key.
     * Switches to BS digital satellite broadcasting service available in Japan. */
    public static final int KEYCODE_TV_SATELLITE_BS = 238;
    /** Key code constant: CS key.
     * Switches to CS digital satellite broadcasting service available in Japan. */
    public static final int KEYCODE_TV_SATELLITE_CS = 239;
    /** Key code constant: BS/CS key.
     * Toggles between BS and CS digital satellite services. */
    public static final int KEYCODE_TV_SATELLITE_SERVICE = 240;
    /** Key code constant: Toggle Network key.
     * Toggles selecting broacast services. */
    public static final int KEYCODE_TV_NETWORK = 241;
    /** Key code constant: Antenna/Cable key.
     * Toggles broadcast input source between antenna and cable. */
    public static final int KEYCODE_TV_ANTENNA_CABLE = 242;
    /** Key code constant: HDMI #1 key.
     * Switches to HDMI input #1. */
    public static final int KEYCODE_TV_INPUT_HDMI_1 = 243;
    /** Key code constant: HDMI #2 key.
     * Switches to HDMI input #2. */
    public static final int KEYCODE_TV_INPUT_HDMI_2 = 244;
    /** Key code constant: HDMI #3 key.
     * Switches to HDMI input #3. */
    public static final int KEYCODE_TV_INPUT_HDMI_3 = 245;
    /** Key code constant: HDMI #4 key.
     * Switches to HDMI input #4. */
    public static final int KEYCODE_TV_INPUT_HDMI_4 = 246;
    /** Key code constant: Composite #1 key.
     * Switches to composite video input #1. */
    public static final int KEYCODE_TV_INPUT_COMPOSITE_1 = 247;
    /** Key code constant: Composite #2 key.
     * Switches to composite video input #2. */
    public static final int KEYCODE_TV_INPUT_COMPOSITE_2 = 248;
    /** Key code constant: Component #1 key.
     * Switches to component video input #1. */
    public static final int KEYCODE_TV_INPUT_COMPONENT_1 = 249;
    /** Key code constant: Component #2 key.
     * Switches to component video input #2. */
    public static final int KEYCODE_TV_INPUT_COMPONENT_2 = 250;
    /** Key code constant: VGA #1 key.
     * Switches to VGA (analog RGB) input #1. */
    public static final int KEYCODE_TV_INPUT_VGA_1 = 251;
    /** Key code constant: Audio description key.
     * Toggles audio description off / on. */
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION = 252;
    /** Key code constant: Audio description mixing volume up key.
     * Louden audio description volume as compared with normal audio volume. */
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP = 253;
    /** Key code constant: Audio description mixing volume down key.
     * Lessen audio description volume as compared with normal audio volume. */
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN = 254;
    /** Key code constant: Zoom mode key.
     * Changes Zoom mode (Normal, Full, Zoom, Wide-zoom, etc.) */
    public static final int KEYCODE_TV_ZOOM_MODE = 255;
    /** Key code constant: Contents menu key.
     * Goes to the title list. Corresponds to Contents Menu (0x0B) of CEC User Control
     * Code */
    public static final int KEYCODE_TV_CONTENTS_MENU = 256;
    /** Key code constant: Media context menu key.
     * Goes to the context menu of media contents. Corresponds to Media Context-sensitive
     * Menu (0x11) of CEC User Control Code. */
    public static final int KEYCODE_TV_MEDIA_CONTEXT_MENU = 257;
    /** Key code constant: Timer programming key.
     * Goes to the timer recording menu. Corresponds to Timer Programming (0x54) of
     * CEC User Control Code. */
    public static final int KEYCODE_TV_TIMER_PROGRAMMING = 258;
    /** Key code constant: Help key. */
    public static final int KEYCODE_HELP = 259;
    /** Key code constant: Navigate to previous key.
     * Goes backward by one item in an ordered collection of items. */
    public static final int KEYCODE_NAVIGATE_PREVIOUS = 260;
    /** Key code constant: Navigate to next key.
     * Advances to the next item in an ordered collection of items. */
    public static final int KEYCODE_NAVIGATE_NEXT   = 261;
    /** Key code constant: Navigate in key.
     * Activates the item that currently has focus or expands to the next level of a navigation
     * hierarchy. */
    public static final int KEYCODE_NAVIGATE_IN     = 262;
    /** Key code constant: Navigate out key.
     * Backs out one level of a navigation hierarchy or collapses the item that currently has
     * focus. */
    public static final int KEYCODE_NAVIGATE_OUT    = 263;
    /** Key code constant: Primary stem key for Wear
     * Main power/reset button on watch. */
    public static final int KEYCODE_STEM_PRIMARY = 264;
    /** Key code constant: Generic stem key 1 for Wear */
    public static final int KEYCODE_STEM_1 = 265;
    /** Key code constant: Generic stem key 2 for Wear */
    public static final int KEYCODE_STEM_2 = 266;
    /** Key code constant: Generic stem key 3 for Wear */
    public static final int KEYCODE_STEM_3 = 267;
    /** Key code constant: Directional Pad Up-Left */
    public static final int KEYCODE_DPAD_UP_LEFT    = 268;
    /** Key code constant: Directional Pad Down-Left */
    public static final int KEYCODE_DPAD_DOWN_LEFT  = 269;
    /** Key code constant: Directional Pad Up-Right */
    public static final int KEYCODE_DPAD_UP_RIGHT   = 270;
    /** Key code constant: Directional Pad Down-Right */
    public static final int KEYCODE_DPAD_DOWN_RIGHT = 271;
    /** Key code constant: Skip forward media key. */
    public static final int KEYCODE_MEDIA_SKIP_FORWARD = 272;
    /** Key code constant: Skip backward media key. */
    public static final int KEYCODE_MEDIA_SKIP_BACKWARD = 273;
    /** Key code constant: Step forward media key.
     * Steps media forward, one frame at a time. */
    public static final int KEYCODE_MEDIA_STEP_FORWARD = 274;
    /** Key code constant: Step backward media key.
     * Steps media backward, one frame at a time. */
    public static final int KEYCODE_MEDIA_STEP_BACKWARD = 275;
    /** Key code constant: put device to sleep unless a wakelock is held. */
    public static final int KEYCODE_SOFT_SLEEP = 276;
    /** Key code constant: Cut key. */
    public static final int KEYCODE_CUT = 277;
    /** Key code constant: Copy key. */
    public static final int KEYCODE_COPY = 278;
    /** Key code constant: Paste key. */
    public static final int KEYCODE_PASTE = 279;
    /** Key code constant: Consumed by the system for navigation up */
    public static final int KEYCODE_SYSTEM_NAVIGATION_UP = 280;
    /** Key code constant: Consumed by the system for navigation down */
    public static final int KEYCODE_SYSTEM_NAVIGATION_DOWN = 281;
    /** Key code constant: Consumed by the system for navigation left*/
    public static final int KEYCODE_SYSTEM_NAVIGATION_LEFT = 282;
    /** Key code constant: Consumed by the system for navigation right */
    public static final int KEYCODE_SYSTEM_NAVIGATION_RIGHT = 283;

    private static final int LAST_KEYCODE = KEYCODE_SYSTEM_NAVIGATION_RIGHT;

    // NOTE: If you add a new keycode here you must also add it to:
    //  isSystem()
    //  isWakeKey()
    //  frameworks/native/include/android/keycodes.h
    //  frameworks/native/include/input/InputEventLabels.h
    //  frameworks/base/core/res/res/values/attrs.xml
    //  emulator?
    //  LAST_KEYCODE
    //
    //  Also Android currently does not reserve code ranges for vendor-
    //  specific key codes.  If you have new key codes to have, you
    //  MUST contribute a patch to the open source project to define
    //  those new codes.  This is intended to maintain a consistent
    //  set of key code definitions across all Android devices.

    // Symbolic names of all metakeys in bit order from least significant to most significant.
    // Accordingly there are exactly 32 values in this table.
    private static final String[] META_SYMBOLIC_NAMES = new String[] {
        "META_SHIFT_ON",
        "META_ALT_ON",
        "META_SYM_ON",
        "META_FUNCTION_ON",
        "META_ALT_LEFT_ON",
        "META_ALT_RIGHT_ON",
        "META_SHIFT_LEFT_ON",
        "META_SHIFT_RIGHT_ON",
        "META_CAP_LOCKED",
        "META_ALT_LOCKED",
        "META_SYM_LOCKED",
        "0x00000800",
        "META_CTRL_ON",
        "META_CTRL_LEFT_ON",
        "META_CTRL_RIGHT_ON",
        "0x00008000",
        "META_META_ON",
        "META_META_LEFT_ON",
        "META_META_RIGHT_ON",
        "0x00080000",
        "META_CAPS_LOCK_ON",
        "META_NUM_LOCK_ON",
        "META_SCROLL_LOCK_ON",
        "0x00800000",
        "0x01000000",
        "0x02000000",
        "0x04000000",
        "0x08000000",
        "0x10000000",
        "0x20000000",
        "0x40000000",
        "0x80000000",
    };

    private static final String LABEL_PREFIX = "KEYCODE_";

    /**
     * @deprecated There are now more than MAX_KEYCODE keycodes.
     * Use {@link #getMaxKeyCode()} instead.
     */
    @Deprecated
    public static final int MAX_KEYCODE             = 84;

    /**
     * {@link #getAction} value: the key has been pressed down.
     */
    public static final int ACTION_DOWN             = 0;
    /**
     * {@link #getAction} value: the key has been released.
     */
    public static final int ACTION_UP               = 1;
    /**
     * {@link #getAction} value: multiple duplicate key events have
     * occurred in a row, or a complex string is being delivered.  If the
     * key code is not {#link {@link #KEYCODE_UNKNOWN} then the
     * {#link {@link #getRepeatCount()} method returns the number of times
     * the given key code should be executed.
     * Otherwise, if the key code is {@link #KEYCODE_UNKNOWN}, then
     * this is a sequence of characters as returned by {@link #getCharacters}.
     */
    public static final int ACTION_MULTIPLE         = 2;

    /**
     * SHIFT key locked in CAPS mode.
     * Reserved for use by {@link MetaKeyKeyListener} for a published constant in its API.
     * @hide
     */
    public static final int META_CAP_LOCKED = 0x100;

    /**
     * ALT key locked.
     * Reserved for use by {@link MetaKeyKeyListener} for a published constant in its API.
     * @hide
     */
    public static final int META_ALT_LOCKED = 0x200;

    /**
     * SYM key locked.
     * Reserved for use by {@link MetaKeyKeyListener} for a published constant in its API.
     * @hide
     */
    public static final int META_SYM_LOCKED = 0x400;

    /**
     * Text is in selection mode.
     * Reserved for use by {@link MetaKeyKeyListener} for a private unpublished constant
     * in its API that is currently being retained for legacy reasons.
     * @hide
     */
    public static final int META_SELECTING = 0x800;

    /**
     * <p>This mask is used to check whether one of the ALT meta keys is pressed.</p>
     *
     * @see #isAltPressed()
     * @see #getMetaState()
     * @see #KEYCODE_ALT_LEFT
     * @see #KEYCODE_ALT_RIGHT
     */
    public static final int META_ALT_ON = 0x02;

    /**
     * <p>This mask is used to check whether the left ALT meta key is pressed.</p>
     *
     * @see #isAltPressed()
     * @see #getMetaState()
     * @see #KEYCODE_ALT_LEFT
     */
    public static final int META_ALT_LEFT_ON = 0x10;

    /**
     * <p>This mask is used to check whether the right the ALT meta key is pressed.</p>
     *
     * @see #isAltPressed()
     * @see #getMetaState()
     * @see #KEYCODE_ALT_RIGHT
     */
    public static final int META_ALT_RIGHT_ON = 0x20;

    /**
     * <p>This mask is used to check whether one of the SHIFT meta keys is pressed.</p>
     *
     * @see #isShiftPressed()
     * @see #getMetaState()
     * @see #KEYCODE_SHIFT_LEFT
     * @see #KEYCODE_SHIFT_RIGHT
     */
    public static final int META_SHIFT_ON = 0x1;

    /**
     * <p>This mask is used to check whether the left SHIFT meta key is pressed.</p>
     *
     * @see #isShiftPressed()
     * @see #getMetaState()
     * @see #KEYCODE_SHIFT_LEFT
     */
    public static final int META_SHIFT_LEFT_ON = 0x40;

    /**
     * <p>This mask is used to check whether the right SHIFT meta key is pressed.</p>
     *
     * @see #isShiftPressed()
     * @see #getMetaState()
     * @see #KEYCODE_SHIFT_RIGHT
     */
    public static final int META_SHIFT_RIGHT_ON = 0x80;

    /**
     * <p>This mask is used to check whether the SYM meta key is pressed.</p>
     *
     * @see #isSymPressed()
     * @see #getMetaState()
     */
    public static final int META_SYM_ON = 0x4;

    /**
     * <p>This mask is used to check whether the FUNCTION meta key is pressed.</p>
     *
     * @see #isFunctionPressed()
     * @see #getMetaState()
     */
    public static final int META_FUNCTION_ON = 0x8;

    /**
     * <p>This mask is used to check whether one of the CTRL meta keys is pressed.</p>
     *
     * @see #isCtrlPressed()
     * @see #getMetaState()
     * @see #KEYCODE_CTRL_LEFT
     * @see #KEYCODE_CTRL_RIGHT
     */
    public static final int META_CTRL_ON = 0x1000;

    /**
     * <p>This mask is used to check whether the left CTRL meta key is pressed.</p>
     *
     * @see #isCtrlPressed()
     * @see #getMetaState()
     * @see #KEYCODE_CTRL_LEFT
     */
    public static final int META_CTRL_LEFT_ON = 0x2000;

    /**
     * <p>This mask is used to check whether the right CTRL meta key is pressed.</p>
     *
     * @see #isCtrlPressed()
     * @see #getMetaState()
     * @see #KEYCODE_CTRL_RIGHT
     */
    public static final int META_CTRL_RIGHT_ON = 0x4000;

    /**
     * <p>This mask is used to check whether one of the META meta keys is pressed.</p>
     *
     * @see #isMetaPressed()
     * @see #getMetaState()
     * @see #KEYCODE_META_LEFT
     * @see #KEYCODE_META_RIGHT
     */
    public static final int META_META_ON = 0x10000;

    /**
     * <p>This mask is used to check whether the left META meta key is pressed.</p>
     *
     * @see #isMetaPressed()
     * @see #getMetaState()
     * @see #KEYCODE_META_LEFT
     */
    public static final int META_META_LEFT_ON = 0x20000;

    /**
     * <p>This mask is used to check whether the right META meta key is pressed.</p>
     *
     * @see #isMetaPressed()
     * @see #getMetaState()
     * @see #KEYCODE_META_RIGHT
     */
    public static final int META_META_RIGHT_ON = 0x40000;

    /**
     * <p>This mask is used to check whether the CAPS LOCK meta key is on.</p>
     *
     * @see #isCapsLockOn()
     * @see #getMetaState()
     * @see #KEYCODE_CAPS_LOCK
     */
    public static final int META_CAPS_LOCK_ON = 0x100000;

    /**
     * <p>This mask is used to check whether the NUM LOCK meta key is on.</p>
     *
     * @see #isNumLockOn()
     * @see #getMetaState()
     * @see #KEYCODE_NUM_LOCK
     */
    public static final int META_NUM_LOCK_ON = 0x200000;

    /**
     * <p>This mask is used to check whether the SCROLL LOCK meta key is on.</p>
     *
     * @see #isScrollLockOn()
     * @see #getMetaState()
     * @see #KEYCODE_SCROLL_LOCK
     */
    public static final int META_SCROLL_LOCK_ON = 0x400000;

    /**
     * This mask is a combination of {@link #META_SHIFT_ON}, {@link #META_SHIFT_LEFT_ON}
     * and {@link #META_SHIFT_RIGHT_ON}.
     */
    public static final int META_SHIFT_MASK = META_SHIFT_ON
            | META_SHIFT_LEFT_ON | META_SHIFT_RIGHT_ON;

    /**
     * This mask is a combination of {@link #META_ALT_ON}, {@link #META_ALT_LEFT_ON}
     * and {@link #META_ALT_RIGHT_ON}.
     */
    public static final int META_ALT_MASK = META_ALT_ON
            | META_ALT_LEFT_ON | META_ALT_RIGHT_ON;

    /**
     * This mask is a combination of {@link #META_CTRL_ON}, {@link #META_CTRL_LEFT_ON}
     * and {@link #META_CTRL_RIGHT_ON}.
     */
    public static final int META_CTRL_MASK = META_CTRL_ON
            | META_CTRL_LEFT_ON | META_CTRL_RIGHT_ON;

    /**
     * This mask is a combination of {@link #META_META_ON}, {@link #META_META_LEFT_ON}
     * and {@link #META_META_RIGHT_ON}.
     */
    public static final int META_META_MASK = META_META_ON
            | META_META_LEFT_ON | META_META_RIGHT_ON;

    /**
     * This mask is set if the device woke because of this key event.
     *
     * @deprecated This flag will never be set by the system since the system
     * consumes all wake keys itself.
     */
    @Deprecated
    public static final int FLAG_WOKE_HERE = 0x1;

    /**
     * This mask is set if the key event was generated by a software keyboard.
     */
    public static final int FLAG_SOFT_KEYBOARD = 0x2;

    /**
     * This mask is set if we don't want the key event to cause us to leave
     * touch mode.
     */
    public static final int FLAG_KEEP_TOUCH_MODE = 0x4;

    /**
     * This mask is set if an event was known to come from a trusted part
     * of the system.  That is, the event is known to come from the user,
     * and could not have been spoofed by a third party component.
     */
    public static final int FLAG_FROM_SYSTEM = 0x8;

    /**
     * This mask is used for compatibility, to identify enter keys that are
     * coming from an IME whose enter key has been auto-labelled "next" or
     * "done".  This allows TextView to dispatch these as normal enter keys
     * for old applications, but still do the appropriate action when
     * receiving them.
     */
    public static final int FLAG_EDITOR_ACTION = 0x10;

    /**
     * When associated with up key events, this indicates that the key press
     * has been canceled.  Typically this is used with virtual touch screen
     * keys, where the user can slide from the virtual key area on to the
     * display: in that case, the application will receive a canceled up
     * event and should not perform the action normally associated with the
     * key.  Note that for this to work, the application can not perform an
     * action for a key until it receives an up or the long press timeout has
     * expired.
     */
    public static final int FLAG_CANCELED = 0x20;

    /**
     * This key event was generated by a virtual (on-screen) hard key area.
     * Typically this is an area of the touchscreen, outside of the regular
     * display, dedicated to "hardware" buttons.
     */
    public static final int FLAG_VIRTUAL_HARD_KEY = 0x40;

    /**
     * This flag is set for the first key repeat that occurs after the
     * long press timeout.
     */
    public static final int FLAG_LONG_PRESS = 0x80;

    /**
     * Set when a key event has {@link #FLAG_CANCELED} set because a long
     * press action was executed while it was down.
     */
    public static final int FLAG_CANCELED_LONG_PRESS = 0x100;

    /**
     * Set for {@link #ACTION_UP} when this event's key code is still being
     * tracked from its initial down.  That is, somebody requested that tracking
     * started on the key down and a long press has not caused
     * the tracking to be canceled.
     */
    public static final int FLAG_TRACKING = 0x200;

    /**
     * Set when a key event has been synthesized to implement default behavior
     * for an event that the application did not handle.
     * Fallback key events are generated by unhandled trackball motions
     * (to emulate a directional keypad) and by certain unhandled key presses
     * that are declared in the key map (such as special function numeric keypad
     * keys when numlock is off).
     */
    public static final int FLAG_FALLBACK = 0x400;

    /**
     * Signifies that the key is being predispatched.
     * @hide
     */
    public static final int FLAG_PREDISPATCH = 0x20000000;

    /**
     * Private control to determine when an app is tracking a key sequence.
     * @hide
     */
    public static final int FLAG_START_TRACKING = 0x40000000;

    /**
     * Private flag that indicates when the system has detected that this key event
     * may be inconsistent with respect to the sequence of previously delivered key events,
     * such as when a key up event is sent but the key was not down.
     *
     * @hide
     * @see #isTainted
     * @see #setTainted
     */
    public static final int FLAG_TAINTED = 0x80000000;
    
    private static HashMap<Integer, Integer> sKeyMap = new HashMap<>();
	
	static {
		sKeyMap.put(KeyEvent.VK_0, KEYCODE_0);
		sKeyMap.put(KeyEvent.VK_1, KEYCODE_1);
		sKeyMap.put(KeyEvent.VK_2, KEYCODE_2);
		sKeyMap.put(KeyEvent.VK_3, KEYCODE_3);
		sKeyMap.put(KeyEvent.VK_4, KEYCODE_4);
		sKeyMap.put(KeyEvent.VK_5, KEYCODE_5);
		sKeyMap.put(KeyEvent.VK_6, KEYCODE_6);
		sKeyMap.put(KeyEvent.VK_7, KEYCODE_7);
		sKeyMap.put(KeyEvent.VK_8, KEYCODE_8);
		sKeyMap.put(KeyEvent.VK_9, KEYCODE_9);
		sKeyMap.put((int)'*', KEYCODE_STAR);
		sKeyMap.put(KeyEvent.VK_NUMBER_SIGN, KEYCODE_POUND);
		sKeyMap.put(KeyEvent.VK_LEFT, KEYCODE_DPAD_LEFT);
		sKeyMap.put(KeyEvent.VK_UP, KEYCODE_DPAD_UP);
		sKeyMap.put(KeyEvent.VK_RIGHT, KEYCODE_DPAD_RIGHT);
		sKeyMap.put(KeyEvent.VK_DOWN, KEYCODE_DPAD_DOWN);
		sKeyMap.put(KeyEvent.VK_A, KEYCODE_A);
		sKeyMap.put(KeyEvent.VK_B, KEYCODE_B);
		sKeyMap.put(KeyEvent.VK_C, KEYCODE_C);
		sKeyMap.put(KeyEvent.VK_D, KEYCODE_D);
		sKeyMap.put(KeyEvent.VK_E, KEYCODE_E);
		sKeyMap.put(KeyEvent.VK_F, KEYCODE_F);
		sKeyMap.put(KeyEvent.VK_G, KEYCODE_G);
		sKeyMap.put(KeyEvent.VK_H, KEYCODE_H);
		sKeyMap.put(KeyEvent.VK_I, KEYCODE_I);
		sKeyMap.put(KeyEvent.VK_J, KEYCODE_J);
		sKeyMap.put(KeyEvent.VK_K, KEYCODE_K);
		sKeyMap.put(KeyEvent.VK_L, KEYCODE_L);
		sKeyMap.put(KeyEvent.VK_M, KEYCODE_M);
		sKeyMap.put(KeyEvent.VK_N, KEYCODE_N);
		sKeyMap.put(KeyEvent.VK_O, KEYCODE_O);
		sKeyMap.put(KeyEvent.VK_P, KEYCODE_P);
		sKeyMap.put(KeyEvent.VK_Q, KEYCODE_Q);
		sKeyMap.put(KeyEvent.VK_R, KEYCODE_R);
		sKeyMap.put(KeyEvent.VK_S, KEYCODE_S);
		sKeyMap.put(KeyEvent.VK_T, KEYCODE_T);
		sKeyMap.put(KeyEvent.VK_U, KEYCODE_U);
		sKeyMap.put(KeyEvent.VK_V, KEYCODE_V);
		sKeyMap.put(KeyEvent.VK_W, KEYCODE_W);
		sKeyMap.put(KeyEvent.VK_X, KEYCODE_X);
		sKeyMap.put(KeyEvent.VK_Y, KEYCODE_Y);
		sKeyMap.put(KeyEvent.VK_Z, KEYCODE_Z);
		
		sKeyMap.put(KeyEvent.VK_COMMA, KEYCODE_COMMA);
		sKeyMap.put(KeyEvent.VK_PERIOD, KEYCODE_PERIOD);
		sKeyMap.put(KeyEvent.VK_SPACE, KEYCODE_SPACE);
		sKeyMap.put(KeyEvent.VK_ENTER, KEYCODE_ENTER);
		sKeyMap.put(KeyEvent.VK_BACK_SPACE, KEYCODE_DEL);
		sKeyMap.put(KeyEvent.VK_DELETE, KEYCODE_FORWARD_DEL);
		sKeyMap.put(KeyEvent.VK_MINUS, KEYCODE_MINUS);
		sKeyMap.put(KeyEvent.VK_PLUS, KEYCODE_PLUS);
		sKeyMap.put(KeyEvent.VK_MULTIPLY, KEYCODE_NUMPAD_MULTIPLY);
		sKeyMap.put(KeyEvent.VK_EQUALS, KEYCODE_EQUALS);
		sKeyMap.put(KeyEvent.VK_BRACELEFT, KEYCODE_LEFT_BRACKET);
		sKeyMap.put(KeyEvent.VK_BRACERIGHT, KEYCODE_RIGHT_BRACKET);
		sKeyMap.put(KeyEvent.VK_BACK_SLASH, KEYCODE_BACKSLASH);
		sKeyMap.put(KeyEvent.VK_SLASH, KEYCODE_SLASH);
		sKeyMap.put(KeyEvent.VK_SEMICOLON, KEYCODE_SEMICOLON);
		sKeyMap.put((int)'\'', KEYCODE_APOSTROPHE);
		sKeyMap.put(KeyEvent.VK_AT, KEYCODE_AT);
		sKeyMap.put(KeyEvent.VK_CONTEXT_MENU, KEYCODE_MENU);
		sKeyMap.put(KeyEvent.VK_PAGE_DOWN, KEYCODE_PAGE_DOWN);
		sKeyMap.put(KeyEvent.VK_PAGE_UP, KEYCODE_PAGE_UP);
		sKeyMap.put(KeyEvent.VK_ESCAPE, KEYCODE_ESCAPE);
		sKeyMap.put(KeyEvent.VK_CAPS_LOCK, KEYCODE_CAPS_LOCK);
		sKeyMap.put(KeyEvent.VK_SCROLL_LOCK, KEYCODE_SCROLL_LOCK);
		sKeyMap.put(KeyEvent.VK_PRINTSCREEN, KEYCODE_SYSRQ);
		sKeyMap.put(KeyEvent.VK_HOME, KEYCODE_MOVE_HOME);
		sKeyMap.put(KeyEvent.VK_END, KEYCODE_MOVE_END);
		sKeyMap.put(KeyEvent.VK_INSERT, KEYCODE_INSERT);
		sKeyMap.put(KeyEvent.VK_F1, KEYCODE_F1);
		sKeyMap.put(KeyEvent.VK_F2, KEYCODE_F2);
		sKeyMap.put(KeyEvent.VK_F3, KEYCODE_F3);
		sKeyMap.put(KeyEvent.VK_F4, KEYCODE_F4);
		sKeyMap.put(KeyEvent.VK_F5, KEYCODE_F5);
		sKeyMap.put(KeyEvent.VK_F6, KEYCODE_F6);
		sKeyMap.put(KeyEvent.VK_F7, KEYCODE_F7);
		sKeyMap.put(KeyEvent.VK_F8, KEYCODE_F8);
		sKeyMap.put(KeyEvent.VK_F9, KEYCODE_F9);
		sKeyMap.put(KeyEvent.VK_F10, KEYCODE_F10);
		sKeyMap.put(KeyEvent.VK_F11, KEYCODE_F11);
		sKeyMap.put(KeyEvent.VK_F12, KEYCODE_F12);
		sKeyMap.put(KeyEvent.VK_NUM_LOCK, KEYCODE_NUM);
		sKeyMap.put(KeyEvent.VK_NUMPAD0, KEYCODE_NUMPAD_0);
		sKeyMap.put(KeyEvent.VK_NUMPAD1, KEYCODE_NUMPAD_1);
		sKeyMap.put(KeyEvent.VK_NUMPAD2, KEYCODE_NUMPAD_2);
		sKeyMap.put(KeyEvent.VK_NUMPAD3, KEYCODE_NUMPAD_3);
		sKeyMap.put(KeyEvent.VK_NUMPAD4, KEYCODE_NUMPAD_4);
		sKeyMap.put(KeyEvent.VK_NUMPAD5, KEYCODE_NUMPAD_5);
		sKeyMap.put(KeyEvent.VK_NUMPAD6, KEYCODE_NUMPAD_6);
		sKeyMap.put(KeyEvent.VK_NUMPAD7, KEYCODE_NUMPAD_7);
		sKeyMap.put(KeyEvent.VK_NUMPAD8, KEYCODE_NUMPAD_8);
		sKeyMap.put(KeyEvent.VK_NUMPAD9, KEYCODE_NUMPAD_9);
		sKeyMap.put(KeyEvent.VK_DIVIDE, KEYCODE_NUMPAD_DIVIDE);
	}
	
	public static int keyMaping(int key) {
		if (!sKeyMap.containsKey(key)) return -1;
		return sKeyMap.get(key);
	}
}
