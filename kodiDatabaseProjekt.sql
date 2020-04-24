USE [master]
GO

/****** Object:  Database [LibraryDataBase] ******/
CREATE DATABASE [LibraryDataBase]

USE [LibraryDataBase]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lexuesit](
	[ID] [int] NOT NULL check ( ID between 0 AND 4000),
	[Regjisrimi] [Date] NOT NULL,
	[Sektori] [varchar] (20) NOT NULL,
	[EmridheMbiemri] [varchar] (40) NOT NULL,
	[Profesioni] [varchar] (20) NOT NULL,
	[Adresa] [varchar] (20) NOT NULL,
	[Cmimi] [Double] NOT NULL
 CONSTRAINT [PK_Lexuesit] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[MbajtesiteLibrave](
	[MarrjaLibritID] [int] NOT NULL,
	[ISBNKodi] [INT] NOT NULL,
	[EmriLibrit] [varchar] (40) NOT NULL,
	[AutoriLibrit] [varchar] (40) NOT NULL,
	[DataMarrjes] [Date] NOT NULL,
	[DataeKthimit] [Date] NOT NULL,
	[EmriPuntorit] [varchar] (30) NOT NULL
	
 CONSTRAINT [PK_MbajtesiteLibrave] PRIMARY KEY CLUSTERED 
(
	[MarrjaLibritID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


CREATE TABLE [dbo].[Vendet](
	[VendiID] [int] NOT NULL CHECK (VendiID BETWEEN 1 AND 4000) ,
	[EmriPersonitUlur] [varchar]  NOT NULL,
	[KohaZeniesVendit] [TIME]  NOT NULL,
	[KohaLirimitVendit] [TIME]  NOT NULL
	
 CONSTRAINT [PK_Vendet] PRIMARY KEY CLUSTERED 
(
	[VendiID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[RegjistrimiLibrave](
	[RegjistrimiID] [int] NOT NULL CHECK (RegjistrimiID BETWEEN 1 AND 4000) ,
	[ISBNKodi] [INT] NOT NULL,
	[EmriLibrit] [varchar] (40) NOT NULL,
	[AutoriLibrit] [varchar] (40) NOT NULL,
	[SasiaNeStoke] [int] NOT NULL
	
 CONSTRAINT [PK_RegjistrimiLibrave] PRIMARY KEY CLUSTERED 
(
	[RegjistrimiID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
