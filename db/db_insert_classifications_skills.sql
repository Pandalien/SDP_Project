INSERT INTO classification (name) VALUES('MANAGERS');
INSERT INTO classification (name) VALUES('PROFESSIONALS');
INSERT INTO classification (name) VALUES('TECHNICIANS AND ASSOCIATE PROFESSIONALS');
INSERT INTO classification (name) VALUES('SERVICE AND SALES WORKERS');
INSERT INTO classification (name) VALUES('CLERICAL SUPPORT WORKERS');
INSERT INTO classification (name) VALUES('SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS');
INSERT INTO classification (name) VALUES('CRAFT AND RELATED TRADES');
INSERT INTO classification (name) VALUES('PLANT AND MACHINE OPERATORS AND ASSEMBLERS');
INSERT INTO classification (name) VALUES('ELEMENTARY OCCUPATIONS');
INSERT INTO classification (name) VALUES('ARMED FORCES OCCUPATIONS');


INSERT INTO skills (name, classification_id) VALUES('Chief executives, senior officials and legislators',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Legislators and senior officials',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Managing directors and chief executives',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Administrative and commercial managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Business services and administration managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Sales, marketing and development managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Production and specialized services managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Production managers in agriculture, forestry and fisheries',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Manufacturing, mining, construction, and distribution managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Information and communications technology service managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Professional services managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Hospitality, retail and other services managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Hotel and restaurant managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Retail and wholesale trade managers',(SELECT id FROM classification WHERE name='MANAGERS'));
INSERT INTO skills (name, classification_id) VALUES('Other services managers',(SELECT id FROM classification WHERE name='MANAGERS'));


INSERT INTO skills (name, classification_id) VALUES('Science and engineering professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Physical and earth science professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Mathematicians, actuaries and statisticians',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Life science professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Engineering professionals (excluding electrotechnology)',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Electrotechnology engineers',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Architects, planners, surveyors and designers',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Health professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Medical doctors',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Nursing and midwifery professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Traditional and complementary medicine professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Paramedical practitioners',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Veterinarians',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Other health professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Medical Assistant professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Teaching professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('University and higher education teachers',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Vocational education teachers',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Secondary education teachers',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Primary school and early childhood teachers',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Other teaching professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Business and administration professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Finance professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Administration professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Sales, marketing and public relations professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Information and communications technology professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Software and applications developers and analysts',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Database and network professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Legal, social and cultural professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Legal professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Librarians, archivists and curators',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Social and religious professionals',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Authors, journalists and linguists',(SELECT id FROM classification WHERE name='PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Creative and performing artists',(SELECT id FROM classification WHERE name='PROFESSIONALS'));


INSERT INTO skills (name, classification_id) VALUES('Science and engineering associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Physical and engineering science technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Mining, manufacturing and construction supervisors',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Process control technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Life science technicians and related associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Ship and aircraft controllers and technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Health associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Medical and pharmaceutical technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Nursing and midwifery associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Traditional and complementary medicine associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Veterinary technicians and assistants',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Other health associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Business and administration associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Financial and mathematical associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Sales and purchasing agents and brokers',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Business services agents',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Administrative and specialized secretaries',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Regulatory government associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Legal, social, cultural and related associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Legal, social and religious associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Sports and fitness workers',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Artistic, cultural and culinary associate professionals',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Information and communications technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Information and communications technology operations and user support technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));
INSERT INTO skills (name, classification_id) VALUES('Telecommunications and broadcasting technicians',(SELECT id FROM classification WHERE name='TECHNICIANS AND ASSOCIATE PROFESSIONALS'));


INSERT INTO skills (name, classification_id) VALUES('General and keyboard clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('General office clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Secretaries (general)',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Keyboard operators',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Customer services clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Tellers, money collectors and related clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Client information workers',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Numerical and material recording clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Numerical clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Material-recording and transport clerks',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Other clerical support workers',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Other clerical support workers',(SELECT id FROM classification WHERE name='CLERICAL SUPPORT WORKERS'));


INSERT INTO skills (name, classification_id) VALUES('Personal service workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Travel attendants, conductors and guides',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Cooks',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Waiters and bartenders',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Hairdressers, beauticians and related workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Building and housekeeping supervisors',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Other personal services workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Sales workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Street and market salespersons',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Shop salespersons',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Cashiers and ticket clerks',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Other sales workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Personal care workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Child care workers and teachers aides',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Personal care workers in health services',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Protective services workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Protective services workers',(SELECT id FROM classification WHERE name='SERVICE AND SALES WORKERS'));


INSERT INTO skills (name, classification_id) VALUES('Market-oriented skilled agricultural workers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Market gardeners and crop growers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Animal producers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Mixed crop and animal producers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Market-oriented skilled forestry, fishery and hunting workers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Forestry and related workers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Fishery workers, hunters and trappers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Subsistence farmers, fishers, hunters and gatherers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Subsistence crop farmers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Subsistence livestock farmers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Subsistence mixed crop and livestock farmers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));
INSERT INTO skills (name, classification_id) VALUES('Subsistence fishers, hunters, trappers and gatherers',(SELECT id FROM classification WHERE name='SKILLED AGRICULTURAL, FORESTRY AND FISHERY WORKERS'));


INSERT INTO skills (name, classification_id) VALUES('Building and related trades workers, excluding electricians',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Building frame and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Building finishers and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Painters, building structure cleaners and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Metal, machinery and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Sheet and structural metal workers, moulders and welders, and related workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Blacksmiths, toolmakers and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Machinery mechanics and repairers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Handicraft and printing workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Handicraft workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Printing trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Electrical and electronic trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Electrical equipment installers and repairers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Electronics and telecommunications installers and repairers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Food processing, wood working, garment and other craft and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Food processing and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Wood treaters, cabinet-makers and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Garment and related trades workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));
INSERT INTO skills (name, classification_id) VALUES('Other craft and related workers',(SELECT id FROM classification WHERE name='CRAFT AND RELATED TRADES'));


INSERT INTO skills (name, classification_id) VALUES('Stationary plant and machine operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Mining and mineral processing plant operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Metal processing and finishing plant operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Chemical and photographic products plant and machine operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Rubber, plastic and paper products machine operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Textile, fur and leather products machine operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Food and related products machine operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Wood processing and papermaking plant operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Other stationary plant and machine operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Assemblers',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Assemblers',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Drivers and mobile plant operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Locomotive engine drivers and related workers',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Car, van and motorcycle drivers',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Heavy truck and bus drivers',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Mobile plant operators',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));
INSERT INTO skills (name, classification_id) VALUES('Ships deck crews and related workers',(SELECT id FROM classification WHERE name='PLANT AND MACHINE OPERATORS AND ASSEMBLERS'));


INSERT INTO skills (name, classification_id) VALUES('Cleaners and helpers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Domestic, hotel and office cleaners and helpers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Vehicle, window, laundry and other hand cleaning workers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Agricultural, forestry and fishery labourers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Agricultural, forestry and fishery labourers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Labourers in mining, construction, manufacturing and transport',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Mining and construction labourers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Manufacturing labourers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Transport and storage labourers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Food preparation assistants',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Food preparation assistants',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Street and related sales and service workers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Street and related service workers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Street vendors (excluding food)',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Refuse workers and other elementary workers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Refuse workers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Other elementary workers',(SELECT id FROM classification WHERE name='ELEMENTARY OCCUPATIONS'));


INSERT INTO skills (name, classification_id) VALUES('Commissioned armed forces officers',(SELECT id FROM classification WHERE name='ARMED FORCES OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Commissioned armed forces officers',(SELECT id FROM classification WHERE name='ARMED FORCES OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Non-commissioned armed forces officers',(SELECT id FROM classification WHERE name='ARMED FORCES OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Non-commissioned armed forces officers',(SELECT id FROM classification WHERE name='ARMED FORCES OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Armed forces occupations, other ranks',(SELECT id FROM classification WHERE name='ARMED FORCES OCCUPATIONS'));
INSERT INTO skills (name, classification_id) VALUES('Armed forces occupations, other ranks',(SELECT id FROM classification WHERE name='ARMED FORCES OCCUPATIONS'));