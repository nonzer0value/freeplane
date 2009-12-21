; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!
;****************************************************************************
;* Install Script for FreeMind
;****************************************************************************
;* Before using this be sure to download and install Inno Setup from
;* www.jrsoftware.org and ISTool from www.istool.org. These are required to
;* make changes and compile this script. To use the billboard feature please
;* dowload and install the ISX BillBoard DLL.
;****************************************************************************
; Predrag Cuklin 18/06/2009 - Universial Version
;****************************************************************************

#define MyVersion "1.0.37 rc"
#define MyAppName "Freeplane"
#define MyAppVerName "Freeplane"
#define MyAppPublisher "Open source"
#define MyAppURL "http://sourceforge.net/projects/freeplane/"
#define MyAppExeName "freeplane.exe"
[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{D3941722-C4DD-4509-88C4-0E87F675A859}
AppCopyright=Copyright � 2000-2009 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev, Predrag Cuklin  and others.
AppName={#MyAppName}
AppVerName={#MyAppVerName}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
OutputDir=..\dist
OutputBaseFilename=Freeplane-Setup-{#MyVersion}
SetupIconFile=Setup.ico
VersionInfoDescription=Amazing open source program for mind mapping
ChangesAssociations=true
PrivilegesRequired=none
AllowNoIcons=true
ShowTasksTreeLines=true
WindowVisible=true
WizardSmallImageFile=Freeplane_bee.bmp
WizardImageStretch=false
AppVersion={#MyVersion}
UninstallDisplayIcon={app}\freeplane.exe
UninstallDisplayName=Freeplane
DiskSpanning=false
MergeDuplicateFiles=true
Compression=lzma
SolidCompression=true
LanguageDetectionMethod=locale
WizardImageFile=WizModernImage-IS.bmp

[Languages]
Name: english; MessagesFile: compiler:Default.isl,messages_en.txt; LicenseFile: gpl-2.0_english.txt
Name: croatian; MessagesFile: compiler:Languages\Croatian.isl,messages_hr.txt; LicenseFile: gpl-2.0_croatian.txt
Name: french; MessagesFile: compiler:Languages\French.isl,messages_fr.txt; LicenseFile: gpl-2.0_french.txt
Name: german; MessagesFile: compiler:Languages\German.isl,messages_de.txt; LicenseFile: gpl-2.0_german.txt
Name: russian; MessagesFile: compiler:Languages\Russian.isl,messages_ru.txt; LicenseFile: gpl-2.0_russian.txt

[Tasks]
Name: desktopicon; Description: {cm:CreateDesktopIcon}; GroupDescription: {cm:AdditionalIcons}
Name: quicklaunchicon; Description: {cm:CreateQuickLaunchIcon}; GroupDescription: {cm:AdditionalIcons}
Name: associate; Description: {cm:AssocFileExtension,Freeplane,.mm}; GroupDescription: {cm:AssocingFileExtension,Freeplane,.mm}


[Files]
Source: ..\build\*; DestDir: {app}; Flags: ignoreversion recursesubdirs createallsubdirs; Excludes: fwdir\*
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: {group}\{#MyAppName}; Filename: {app}\{#MyAppExeName}; Tasks: 
Name: {group}\Uninstall Freeplane; Filename: {uninstallexe}; Tasks: 
Name: {commondesktop}\{#MyAppName}; Filename: {app}\{#MyAppExeName}; Tasks: desktopicon
Name: {userappdata}\Microsoft\Internet Explorer\Quick Launch\{#MyAppName}; Filename: {app}\{#MyAppExeName}; Tasks: quicklaunchicon

[Run]
Filename: {app}\{#MyAppExeName}; Description: {cm:LaunchProgram,{#MyAppName}}; Flags: nowait postinstall skipifsilent

[Registry]
;".myp" is the extension we're associating. "MyProgramFile" is the internal name for the file type as stored in the registry. Make sure you use a unique name for this so you don't inadvertently overwrite another application's registry key.
;"My Program File" above is the name for the file type as shown in Explorer.
;"DefaultIcon" is the registry key that specifies the filename containing the icon to associate with the file type. ",0" tells Explorer to use the first icon from MYPROG.EXE. (",1" would mean the second icon.)
Root: HKLM; Subkey: Software\JavaSoft\Prefs
Root: HKCR; Subkey: Applications\freeplane.exe; Flags: deletekey; Tasks: associate
Root: HKCR; Subkey: .mm; Flags: deletekey; Tasks: associate
Root: HKLM; Subkey: SOFTWARE\Classes\.mm; Flags: deletekey; Tasks: associate
Root: HKCU; Subkey: Software\Classes\Applications\freeplane.exe; Flags: deletekey; Tasks: associate
Root: HKCU; Subkey: Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mm; Flags: deletekey; Tasks: associate
Root: HKCR; SubKey: .mm; ValueType: string; ValueData: Freeplane; Flags: uninsdeletekey; Tasks: associate
Root: HKCR; SubKey: Freeplane; ValueType: string; ValueData: Freeplane mind map; Flags: uninsdeletekey; Tasks: associate
Root: HKCR; SubKey: Freeplane\Shell\Open\Command; ValueType: string; ValueData: """{app}\freeplane.exe"" ""%1"""; Flags: uninsdeletevalue; Tasks: associate
Root: HKCR; Subkey: Freeplane\DefaultIcon; ValueType: string; ValueData: {app}\freeplane.exe,1; Flags: uninsdeletevalue; Tasks: associate
[InstallDelete]
Name: {app}\plugins\org.freeplane.core; Type: filesandordirs; Tasks: ; Languages: 



[UninstallDelete]

[Code]
function SearchForJavaVersion: Boolean;
var
  AVersion: String;
begin
  Result := False;
  if RegQueryStringValue(HKEY_LOCAL_MACHINE, 'SOFTWARE\JavaSoft\Java Runtime Environment', 'CurrentVersion', AVersion) then
  begin
	if (AVersion = '1.5') or (AVersion = '1.6') or (AVersion = '1.7') then
		Result := True;
  end;
end;

function CheckJavaVersion: Boolean;
begin
  Result := SearchForJavaVersion;
  if Result = False then	// Java not found/detected
  begin
	if MsgBox( ExpandConstant('{cm:JavaNotFound}'), mbError, MB_YESNO) = MRYES then
		Result := True
	else
		Result := False;
  end;
end;

function InitializeSetup(): Boolean;
begin
	Result := CheckJavaVersion;
end;
